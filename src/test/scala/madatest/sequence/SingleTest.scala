

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class SingleTest {
    def testTrivial: Unit = {
        val t = sequence.single(99)
        val u = sequence.Of(99)
        assertEquals(1, t.size)
        assertEquals(t, u)
        assertEquals(1, t.size)
        assertEquals(t, u)
    }
}
