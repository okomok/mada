

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class UniqueTest {

    def testUnique: Unit = {
        new NotStartable[Int].unique
        val tr = traversable.of(5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique
        assertEquals(sr, traversable.of(5,4,3,2,1))
        assertEquals(sr, traversable.of(5,4,3,2,1)) // traverse again.
    }

    def testFusion: Unit = {
        val tr = traversable.of(5,5,5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique.unique.unique
        assertEquals(sr, traversable.of(5,4,3,2,1))
        assertEquals(sr, traversable.of(5,4,3,2,1)) // traverse again.
    }

    def testUnique0: Unit = {
        val tr = traversable.emptyOf[Int]
        val sr = tr.unique
        assertTrue(tr.isEmpty)
    }

    def testUnique1: Unit = {
        val tr = traversable.of(9)
        val sr = tr.unique
        assertEquals(sr, traversable.of(9))
        assertEquals(sr, traversable.of(9)) // traverse again.
    }

    def testUnique2: Unit = {
        val tr = traversable.of(9,9,9,9,9,9)
        val sr = tr.unique
        assertEquals(sr, traversable.of(9))
        assertEquals(sr, traversable.of(9)) // traverse again.
    }

}
