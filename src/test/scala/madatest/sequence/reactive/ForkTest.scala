

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class ForkTest {
    def testTrivial: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Int]
        r.
            fork(reactor.make(out.add(98), e => out.add(e *  2))).
            fork(reactor.make(out.add(99), e => out.add(e + 10))).
            run

        assertEquals(iterative.Of(2,11,4,12,6,13,8,14,10,15,12,16, 98,99), iterative.from(out))
    }

    def testBy: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Int]
        r.
            forkBy{e => out.add(e * 2)}.
            forkBy{e => out.add(e + 10)}.
            run

        assertEquals(iterative.Of(2,11,4,12,6,13,8,14,10,15,12,16), iterative.from(out))
    }
}
