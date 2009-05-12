

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class ValueTest {
    def testTrivial: Unit = {
        val t: mada.Traversable[Int] = traversable.fromValues(1,2,3)
        val u = traversable.fromValues(1,2,3)
        assertNotSame(t, u)
        assertTrue(t.equalsIf(u)(mada.function.equal))
        assertEquals(t, u)
        assertEquals(3, t.length)
        assertEquals(3, u.length)
        assertEquals(t, u)
        assertEquals(3, t.length)
        assertEquals(3, u.length)
    }

    def testTrivial2: Unit = {
        val t = traversable.fromValues(1,2,3)
        val u = traversable.fromValues(1,2,3,4)
        AssertNotEquals(t, u)
    }

    def testTrivial3: Unit = {
        val t = traversable.fromValues(1,2,3,4,5)
        val u = traversable.emptyOf[Int]
        AssertNotEquals(t, u)
    }

    def testTrivial4: Unit = {
        val t = traversable.fromValues(1,2,3)
        val u = traversable.fromValues(3,4,5)
        AssertNotEquals(t, u)
    }

    def testEmpty: Unit = {
        val t = traversable.emptyOf[Int]
        val u = traversable.emptyOf[Int]
        assertEquals(t, u)
        assertEquals(t, u)
    }
}
