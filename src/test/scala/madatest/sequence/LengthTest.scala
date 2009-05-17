

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class LengthTest {
    def testTrivial: Unit = {
        val tr = sequence.of(1,2,3)
        assertEquals(3, tr.length)
        assertEquals(3, tr.length)
    }

    def testEmpty: Unit = {
        val tr = sequence.emptyOf[Int]
        assertEquals(0, tr.length)
        assertEquals(0, tr.length)
    }
}
