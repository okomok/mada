

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class SliceTest {
    def testTrivial: Unit = {
        val t = traversable.of(4,5,1,3,2,9,7,10)
        val u = traversable.of(4,5,1)
        val v = traversable.of(4,5,1,3,2,9,7,10)
        val w = traversable.of(1,3,2,9)
        val x = traversable.of(1,3,2,9,7,10)
        val k0 = t.slice(3, 3)
        assertTrue(k0.isEmpty)
        assertTrue(k0.isEmpty)
        val k00 = t.slice(0, 0)
        assertTrue(k00.isEmpty)
        assertTrue(k00.isEmpty)
        val k1 = t.slice(0, 3)
        assertEquals(u, k1)
        assertEquals(u, k1)
        val k2 = t.slice(0, 8)
        assertEquals(v, k2)
        assertEquals(v, k2)
        val k3 = t.slice(0, 80)
        assertEquals(v, k3)
        assertEquals(v, k3)
        val k4 = t.slice(2, 6)
        assertEquals(w, k4)
        assertEquals(w, k4)
        val k5 = t.slice(2, 60)
        assertEquals(x, k5)
        assertEquals(x, k5)
    }

    def testEmpty = {
        val k = traversable.emptyOf[Int].slice(4, 4)
        assertTrue(k.isEmpty)
        val k0 = traversable.emptyOf[Int].slice(0, 0)
        assertTrue(k0.isEmpty)
    }
}
