

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vectors
import mada.Vectors.fromArray
import junit.framework.Assert._


class NthTest {
    def testTrivial: Unit = {
        val a = Vectors.range(3, 10)
        assertEquals(3, a(3))
        assertEquals(3, a.nth(0))
        assertEquals(4, a.nth(1))
        assertEquals(5, a.nth(2))
        assertEquals(6, a.nth(3))
    }

    def testNthRegion: Unit = {
        val a = Vectors.range(3, 10).region(5, 7)
        assertEquals(a, a.nth)
    }

    def testFusion: Unit = {
        val a = Vectors.range(3, 10)
        assertEquals(3, a(3))
        assertEquals(3, a.nth.nth(0))
        assertEquals(4, a.nth.nth(1))
        assertEquals(5, a.nth.nth(2))
        assertEquals(6, a.nth.nth.nth.nth(3))
    }
}
