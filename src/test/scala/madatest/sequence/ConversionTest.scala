

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class ConversionTest {
    def testIterable: Unit = {
        val t = sequence.Of(1,2,3)
        val i = t.toSSequence.iterator
        assertEquals(1, i.next)
        assertEquals(2, i.next)
        assertEquals(3, i.next)
        assertFalse(i.hasNext)
    }

    def testIterable0: Unit = {
        val t = sequence.emptyOf[Int]
        val i = t.toSSequence.iterator
        assertFalse(i.hasNext)
    }

    def testVector: Unit = {
        val t = sequence.Of(1,2,3)
        val v = t.toVector
        assertEquals(1, v(0))
        assertEquals(2, v(1))
        assertEquals(3, v(2))

        v(0) = 9 // guarantees newly writable one allocated.
        assertEquals(9, v(0))
    }

    def testVector0: Unit = {
        val t = sequence.emptyOf[Int]
        val v = t.toVector
        assertTrue(v.isEmpty)
    }

    def testToSome: Unit = {
        val t = sequence.Of(1,2,3)
        val x = t.toSome
        x.view
        ()
    }
}
