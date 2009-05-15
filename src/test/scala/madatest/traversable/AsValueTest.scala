

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class AsValueTest {
    def testTrivial: Unit = {
        val t: mada.Traversable[Int] = traversable.of(1,2,3)
        val u = traversable.of(1,2,3)
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
        val t = traversable.of(1,2,3)
        val u = traversable.of(1,2,3,4)
        AssertNotEquals(t, u)
    }

    def testTrivial3: Unit = {
        val t = traversable.of(1,2,3,4,5)
        val u = traversable.emptyOf[Int]
        AssertNotEquals(t, u)
    }

    def testTrivial4: Unit = {
        val t = traversable.of(1,2,3)
        val u = traversable.of(3,4,5)
        AssertNotEquals(t, u)
    }

    def testEmpty: Unit = {
        val t = traversable.emptyOf[Int]
        val u = traversable.emptyOf[Int]
        assertEquals(t, u)
        assertEquals(t, u)
    }

    def testToString: Unit = {
        val t = traversable.of(1,2,3)
        assertEquals("[1, 2, 3]", t.toString)
        assertEquals("[1, 2, 3]", t.toString)
        val t0 = traversable.emptyOf[Int]
        assertEquals("[]", t0.toString)
        assertEquals("[]", t0.toString)
        val t00 = traversable.of()
        assertEquals("[]", t00.toString)
        assertEquals("[]", t00.toString)
        val t1 = traversable.of(1)
        assertEquals("[1]", t1.toString)
        assertEquals("[1]", t1.toString)
    }

    def testStringize: Unit = {
        val t = traversable.of('1','2','3')
        assertEquals("123", t.stringize)
        assertEquals("123", t.stringize)
        val t0 = traversable.emptyOf[Char]
        assertEquals("", t0.stringize)
        assertEquals("", t0.stringize)
        val t1 = traversable.of('1')
        assertEquals("1", t1.stringize)
        assertEquals("1", t1.stringize)
    }

    def testHashCode: Unit = {
        val t = traversable.of(4,7,6)
        val s = traversable.of(4,7,6)
        val u = traversable.of(4,7,9)
        assertEquals(s.hashCode, t.hashCode)
        assertEquals(t.hashCode, t.hashCode)
        AssertNotEquals(u.hashCode, t.hashCode)
    }
}
