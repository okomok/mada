

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import mada.traverser
import junit.framework.Assert._


class TraverserTest {
    def testTrivial: Unit = {
        val tr = traversable.of(2,4,6)
        val t = tr.start
        assertEquals(2, ~t)
        t.++
        assertEquals(4, ~t)
        t.++
        assertEquals(6, ~t)
        t.++
        assertFalse(t)
        val i = t.toIterator
        assertFalse(i.hasNext)
    }

    def testIterator: Unit = {
        val tr = traversable.of(2,4,6)
        val i = tr.start.toIterator
        assertEquals(2, i.next)
        assertEquals(4, i.next)
        assertEquals(6, i.next)
        assertFalse(i.hasNext)
        val t = traverser.fromIterator(i)
        assertFalse(t)
    }

    def testFusion: Unit = {
        val tr = traversable.of(2,4,6)
        val i = traverser.fromIterator(tr.start.toIterator).toIterator
        assertEquals(2, i.next)
        //println("fusion")
        val t1 = traverser.fromIterator(i)
        assertEquals(4, ~t1)
        t1.++
        assertEquals(6, ~t1)
        val i1 = t1.toIterator
        assertEquals(6, i1.next)
        //println("fusion")
        val t2 = traverser.fromIterator(i1)
        assertFalse(t2)
    }
}
