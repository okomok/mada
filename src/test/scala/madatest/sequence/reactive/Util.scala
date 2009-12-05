

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import java.util.concurrent.CyclicBarrier
import java.util.ArrayList


class IntSenders(_data: Vector[Int]*) {
    private val data: Vector[Vector[Int]] = vector.from(_data.toArray[Vector[Int]])
    private val barrier = new CyclicBarrier(data.size + 1)
    private val senders: Vector[IntSender] = {
        val buf = new ArrayList[IntSender]
        for (datum <- data) {
            val s = new IntSender(datum, barrier)
            buf.add(s)
        }
        vector.from(buf)
    }

    def apply(n: Int): IntSender = senders.nth(n)

    def activate: Unit = barrier.await

    def shutdown(f: => Unit) = {
        barrier.await
        f
    }
}


class IntSender(datum: Vector[Int], barrier: CyclicBarrier) extends Reactive[Int] {
    override def activate(k: Reactor[Int]) = {
        new Thread {
            override def run = {
                barrier.await

                for (i <- datum) {
                    k.react(i)
                }
                k.onEnd

                barrier.await
            }
        }.start
    }
}


class IntReceiver(expected: Vector[Int]) extends Reactor[Int] {
    import junit.framework.Assert._

    private val buf = new ArrayList[Int]
    private var endCount = 0
    private var sequential = true

    override def onEnd = synchronized { endCount += 1 }
    override def react(e: Int) = synchronized {if (endCount!=0) sequential = false; buf.add(e); }

    def assertMe = {
        assertEquals("onEnd call must be only once.", 1, endCount)
        assertEquals(expected, vector.from(buf).sort)
        assertTrue("onEnd is called while reacting!", sequential)
    }
}
