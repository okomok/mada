

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class AsValueTest {
    def testTrivial: Unit = {
        val t: iterative.Type[Int] = iterative.Of(1,2,3)
        val u = iterative.Of(1,2,3)
        assertNotSame(t, u)
        assertTrue(t.equalsIf(u)(mada.function.equal))
        assertEquals(t, u)
        assertEquals(3, t.size)
        assertEquals(3, u.size)
        assertEquals(t, u)
        assertEquals(3, t.size)
        assertEquals(3, u.size)
    }

    def testTrivial2: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Type(1,2,3,4)
        AssertNotEquals(t, u)
    }

    def testTrivial3: Unit = {
        val t = iterative.Of(1,2,3,4,5)
        val u = iterative.emptyOf[Int]
        AssertNotEquals(t, u)
    }

    def testTrivial4: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Of(3,4,5)
        AssertNotEquals(t, u)
    }

    def testEmpty: Unit = {
        val t = iterative.emptyOf[Int]
        val u = iterative.emptyOf[Int]
        assertEquals(t, u)
        assertEquals(t, u)
    }

    def testToString: Unit = {
        val t = iterative.Of(1,2,3)
        assertEquals("[1, 2, 3]", t.toString)
        assertEquals("[1, 2, 3]", t.toString)
        val t0 = iterative.emptyOf[Int]
        assertEquals("[]", t0.toString)
        assertEquals("[]", t0.toString)
        val t00 = iterative.Of()
        assertEquals("[]", t00.toString)
        assertEquals("[]", t00.toString)
        val t1 = iterative.Of(1)
        assertEquals("[1]", t1.toString)
        assertEquals("[1]", t1.toString)
    }

    def testStringize: Unit = {
        val t = iterative.Of('1','2','3')
        assertEquals("123", t.stringize)
        assertEquals("123", t.stringize)
        val t0 = iterative.emptyOf[Char]
        assertEquals("", t0.stringize)
        assertEquals("", t0.stringize)
        val t1 = iterative.Of('1')
        assertEquals("1", t1.stringize)
        assertEquals("1", t1.stringize)
    }

    def testHashCode: Unit = {
        val t = iterative.Of(4,7,6)
        val s = iterative.Of(4,7,6)
        val u = iterative.Of(4,7,9)
        assertEquals(s.hashCode, t.hashCode)
        assertEquals(t.hashCode, t.hashCode)
        AssertNotEquals(u.hashCode, t.hashCode)
    }
}
