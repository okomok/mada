

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence.list
import junit.framework.Assert._


class SizeTest {
    def testTrivial: Unit = {
        val tr = list.Of(1,2,3)
        assertEquals(3, tr.size)
        assertEquals(3, tr.size)
    }

    def testEmpty: Unit = {
        val tr = list.emptyOf[Int]
        assertEquals(0, tr.size)
        assertEquals(0, tr.size)
    }
}
