

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class EmptyTest {
    def testTrivial: Unit = {
        val tr = iterative.emptyOf[Int]
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty)
    }
}
