

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class ConversionTest extends junit.framework.TestCase {
    def testIterable: Unit = {
        val t = iterative.Of(1,2,3)
        val i = t.toSeq.iterator
        assertEquals(1, i.next)
        assertEquals(2, i.next)
        assertEquals(3, i.next)
        assertFalse(i.hasNext)
    }

    def testIterable0: Unit = {
        val t = iterative.empty.of[Int]
        val i = t.toSeq.iterator
        assertFalse(i.hasNext)
    }

    def testVector: Unit = {
        val t = iterative.Of(1,2,3)
        val v = t.toVector
        assertEquals(1, v(0))
        assertEquals(2, v(1))
        assertEquals(3, v(2))

        v(0) = 9 // guarantees newly writable one allocated.
        assertEquals(9, v(0))
    }

    def testVector0: Unit = {
        val t = iterative.empty.of[Int]
        val v = t.toVector
        assertTrue(v.isEmpty)
    }

    /* toSome was rejected.
    def testToSome: Unit = {
        val t = iterative.Of(1,2,3)
        val x = t.toSome
        x.view
        ()
    }
    */
}
