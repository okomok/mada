

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import mada.traverser
import junit.framework.Assert._


class TraverserTest {
    def testTrivial: Unit = {
        val tr = traversable.of(2,4,6)
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
        val tr = traversable.of(2,4,6)
        val i = tr.begin.toSIterator
        assertEquals(2, i.next)
        assertEquals(4, i.next)
        assertEquals(6, i.next)
        assertFalse(i.hasNext)
        val t = traverser.fromSIterator(i)
        assertFalse(t)
    }

    def testFusion: Unit = {
        val tr = traversable.of(2,4,6)
        val i = traverser.fromSIterator(tr.begin.toSIterator).toSIterator
        assertEquals(2, i.next)
        //println("fusion")
        val t1 = traverser.fromSIterator(i)
        assertEquals(4, ~t1)
        t1.++
        assertEquals(6, ~t1)
        val i1 = t1.toSIterator
        assertEquals(6, i1.next)
        //println("fusion")
        val t2 = traverser.fromSIterator(i1)
        assertFalse(t2)
    }
}
