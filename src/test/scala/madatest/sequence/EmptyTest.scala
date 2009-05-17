

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class EmptyTest {
    def testTrivial: Unit = {
        val tr = sequence.emptyOf[Int]
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty)
    }
}
