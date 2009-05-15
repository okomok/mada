

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class FilterTest {
    def testTrivial: Unit = {
        val t = traversable.of(1,2,2,3,4,5,5,6,7,8,9)
        val u = traversable.of(2,2,4,6,8)
        val k = t.filter(_ % 2 == 0)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testTrivial2: Unit = {
        val t = traversable.of(2,2,3,4,5,5,6,7,8,9)
        val u = traversable.of(2,2,4,6,8)
        val k = t.filter(_ % 2 == 0)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        val t = traversable.emptyOf[Int]
        val k = t.filter(_ % 2 == 0)
        assertTrue(k.isEmpty)
        assertTrue(k.isEmpty)
    }

    def testEmpty2 = {
        val t = traversable.of(1,2,2,3,4,5,5,6,7,8,9)
        val k = t.filter(_ % 20 == 0)
        assertTrue(k.isEmpty)
        assertTrue(k.isEmpty)
    }

    def testFusion: Unit = {
        val t = traversable.of(1,2,2,3,4,5,5,6,7,8,9)
        val u = traversable.of(4,8)
        val k = t.filter(_ % 2 == 0).filter(_ % 4 == 0)
        assertEquals(u, k)
        assertEquals(u, k)
    }
}
