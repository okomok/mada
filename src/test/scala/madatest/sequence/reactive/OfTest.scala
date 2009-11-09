

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class OfTest {
    def testTrivial: Unit = {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a.subscribe(reactor.make(out.add(99), e => out.add(e)))
        assertEquals(iterative.Of(1,2,3,4,5,99), iterative.from(out))
    }
}
