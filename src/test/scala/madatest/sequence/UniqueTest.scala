

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class UniqueTest {

    def testUnique: Unit = {
        new NotStartable[Int].unique
        val tr = sequence.Of(5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique
        assertEquals(sequence.Of(5,4,3,2,1), sr)
        assertEquals(sequence.Of(5,4,3,2,1), sr) // traverse again.
    }

    def testFusion: Unit = {
        val tr = sequence.Of(5,5,5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique.unique.unique
        assertEquals(sequence.Of(5,4,3,2,1), sr)
        assertEquals(sequence.Of(5,4,3,2,1), sr) // traverse again.
    }

    def testUnique0: Unit = {
        val tr = sequence.emptyOf[Int]
        val sr = tr.unique
        assertTrue(tr.isEmpty)
    }

    def testUnique1: Unit = {
        val tr = sequence.Of(9)
        val sr = tr.unique
        assertEquals(sequence.Of(9), sr)
        assertEquals(sequence.Of(9), sr) // traverse again.
    }

    def testUnique2: Unit = {
        val tr = sequence.Of(9,9,9,9,9,9)
        val sr = tr.unique
        assertEquals(sequence.Of(9), sr)
        assertEquals(sequence.Of(9), sr) // traverse again.
    }

}
