

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class TakeTest {
    def testTrivial: Unit = {
        val t = traversable.of(4,5,1,3,2,9,7,10)
        val u = traversable.of(4,5,1)
        val v = traversable.of(4,5,1,3,2,9,7,10)
        val k0 = t.take(0)
        assertTrue(k0.isEmpty)
        assertTrue(k0.isEmpty)
        val k1 = t.take(3)
        assertEquals(u, k1)
        assertEquals(u, k1)
        val k2 = t.take(8)
        assertEquals(v, k2)
        assertEquals(v, k2)
        val k3 = t.take(80)
        assertEquals(v, k3)
        assertEquals(v, k3)
    }

    def testTrivial2 = {
        val t = traversable.of(14,15,11,3,2,9,7,10)
        val u = traversable.of(14,15,11)
        val v = traversable.of(14,15,11,3,2,9,7,10)
        val k0 = t.takeWhile(_ > 100)
        assertTrue(k0.isEmpty)
        assertTrue(k0.isEmpty)
        val k1 = t.takeWhile(_ > 10)
        assertEquals(u, k1)
        assertEquals(u, k1)
        val k2 = t.takeWhile(_ => true)
        assertEquals(v, k2)
        assertEquals(v, k2)
    }

    def testFusion: Unit = {
        val t = traversable.of(9,7,10,1,2)
        val u = traversable.of(9,7,10)
        val k = t.take(2).take(3)
        assertEquals(k, u)
    }

    def testEmpty: Unit = {
        val k = traversable.emptyOf[Int].take(2)
        assertTrue(k.isEmpty)
        assertTrue(k.isEmpty)
        val k2 = traversable.emptyOf[Int].take(0)
        assertTrue(k2.isEmpty)
        assertTrue(k2.isEmpty)
    }
}
