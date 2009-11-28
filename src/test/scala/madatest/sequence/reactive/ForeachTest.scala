

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class ForeachTest {
    def testTrivial: Unit = {
        val a = iterative.Of(1,6,7,10,14,17)
        val t = new java.util.ArrayList[Int]
        reactive.fromIterative(a).foreach{ e => t.add(e) }
        assertEquals(a, iterative.from(t))
    }

    def testOnEnd: Unit = {
        val a = iterative.Of(1,6,7,10,14,17)
        val t = new java.util.ArrayList[Int]

        val c = new Call(assertEquals(a, iterative.from(t)))

        reactive.fromIterative(a).start(new Reactor[Int] {
            override def onEnd = c()
            override def react(e: Int) = t.add(e)
        })

        assertTrue(c.isCalled)
    }

    def testRun: Unit = {
        val a = iterative.Of(1,6,7,10,14,17)
        val t = new java.util.ArrayList[Int]
        reactive.fromIterative(a).forkTo{ reactor.make(_ => (), e => t.add(e)) }.run
        assertEquals(a, iterative.from(t))
    }
}
