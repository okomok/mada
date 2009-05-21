

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class LengthTest {
    def testTrivial: Unit = {
        val tr = sequence.Of(1,2,3)
        assertEquals(3, tr.size)
        assertEquals(3, tr.size)
    }

    def testEmpty: Unit = {
        val tr = sequence.emptyOf[Int]
        assertEquals(0, tr.size)
        assertEquals(0, tr.size)
    }
}
