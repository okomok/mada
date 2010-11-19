

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

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
    override def forloop(f: Int => Unit, k: => Unit) = {
        new Thread {
            override def run = {
                barrier.await

                for (i <- datum) {
                    f(i)
                    //Thread.sleep(100)
                }

                barrier.await
            }
        }.start
    }
}


class IntReceiver(expected: Vector[Int]) extends Function1[Int, Unit] {
    import junit.framework.Assert._

    private val buf = new ArrayList[Int]

    override def apply(e: Int) = synchronized { buf.add(e) }

    def assertMe = {
        assertTrue("expected: " + expected + " actural: " + vector.from(buf), expected == vector.from(buf).sort)
    }
}
