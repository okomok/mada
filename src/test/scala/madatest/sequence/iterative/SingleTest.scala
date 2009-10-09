

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class SingleTest {
    def testTrivial: Unit = {
        val t = iterative.single(99)
        val u = iterative.Of(99)
        assertEquals(1, t.size)
        assertEquals(t, u)
        assertEquals(1, t.size)
        assertEquals(t, u)
    }
}
