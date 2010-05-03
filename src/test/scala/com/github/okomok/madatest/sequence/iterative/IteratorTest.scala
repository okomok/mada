

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import mada.sequence.iterator
import junit.framework.Assert._


class TraverserTest {
    def testTrivial: Unit = {
        val tr = iterative.Of(2,4,6)
        val t = tr.begin
        assertEquals(2, ~t)
        t.++
        assertEquals(4, ~t)
        t.++
        assertEquals(6, ~t)
        t.++
        assertFalse(t)
        val i = t.toSIterator
        assertFalse(i.hasNext)
    }

    def testIterator: Unit = {
        val tr = iterative.Of(2,4,6)
        val i = tr.begin.toSIterator
        assertEquals(2, i.next)
        assertEquals(4, i.next)
        assertEquals(6, i.next)
        assertFalse(i.hasNext)
        val t = iterator.fromSIterator(i)
        assertFalse(t)
    }

    def testFusion: Unit = {
        val tr = iterative.Of(2,4,6)
        val i = iterator.fromSIterator(tr.begin.toSIterator).toSIterator
        assertEquals(2, i.next)
        //println("fusion")
        val t1 = iterator.fromSIterator(i)
        assertEquals(4, ~t1)
        t1.++
        assertEquals(6, ~t1)
        val i1 = t1.toSIterator
        assertEquals(6, i1.next)
        //println("fusion")
        val t2 = iterator.fromSIterator(i1)
        assertFalse(t2)
    }
}
