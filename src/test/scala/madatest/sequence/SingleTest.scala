

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class SingleTest {
    def testTrivial: Unit = {
        val t = sequence.single(99)
        val u = sequence.of(99)
        assertEquals(1, t.length)
        assertEquals(t, u)
        assertEquals(1, t.length)
        assertEquals(t, u)
    }
}
