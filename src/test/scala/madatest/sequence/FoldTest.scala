

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class FoldTest {
    def testFoldLeft: Unit = {
        val tr = sequence.of(2,4,6)
        assertEquals(1+2+4+6, tr.foldLeft(1)(_ + _))
    }

    def testFoldLeft0: Unit = {
        val tr = sequence.emptyOf[Int]
        assertEquals(1, tr.foldLeft(1)(_ + _))
    }

    def testReduceLeft: Unit = {
        val tr = sequence.of(2,4,6)
        assertEquals(2+4+6, tr.reduceLeft(_ + _))
    }
}