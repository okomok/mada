

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class ConversionTest {
    def testIterable: Unit = {
        val t = traversable.of(1,2,3)
        val it = t.toIterable
        val i = it.elements
        assertEquals(1, i.next)
        assertEquals(2, i.next)
        assertEquals(3, i.next)
        assertFalse(i.hasNext)
    }

    def testIterable0: Unit = {
        val t = traversable.emptyOf[Int]
        val it = t.toIterable
        val i = it.elements
        assertFalse(i.hasNext)
    }

    def testVector: Unit = {
        val t = traversable.of(1,2,3)
        val v = t.toVector
        assertEquals(1, v(0))
        assertEquals(2, v(1))
        assertEquals(3, v(2))
        v(0) = 9 // guarantees writable.
        assertEquals(9, v(0))
    }

    def testVector0: Unit = {
        val t = traversable.emptyOf[Int]
        val v = t.toVector
        assertTrue(v.isEmpty)
    }
}
