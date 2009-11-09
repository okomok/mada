

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
        reactive.fromIterative(a).subscribe(new Reactor[Int] {
            override def onEnd = t.add(99)
            override def react(e: Int) = t.add(e)
        })
        assertEquals(a ++ iterative.single(99), iterative.from(t))
    }
}
