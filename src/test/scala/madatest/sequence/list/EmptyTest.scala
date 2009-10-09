

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence.list
import junit.framework.Assert._


class EmptyTest {
    def testTrivial: Unit = {
        val tr = list.emptyOf[Int]
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty)
    }
}
