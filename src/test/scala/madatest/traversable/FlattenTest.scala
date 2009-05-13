

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class FlatenTest {
    def testTrivial = {
        val t1 = traversable.fromValues(0,1,2)
        val t2 = traversable.fromValues(3,4)
        val t3 = traversable.emptyOf[Int]
        val t4 = traversable.fromValues(5,6)
        val t5 = traversable.fromValues(7,8,9,10)
        val t = traversable.fromValues(t1, t2, t3, t4, t5).flatten
        val a = traversable.fromValues(0,1,2,3,4,5,6,7,8,9,10)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty = {
        val t1 = traversable.emptyOf[Int]
        val t2 = traversable.fromValues(3,4)
        val t3 = traversable.emptyOf[Int]
        val t4 = traversable.emptyOf[Int]
        val t = traversable.fromValues(t1, t2, t3, t4).flatten
        val a = traversable.fromValues(3,4)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty2 = {
        val t1 = traversable.emptyOf[Int]
        val t2 = traversable.emptyOf[Int]
        val t3 = traversable.emptyOf[Int]
        val t = traversable.fromValues(t1, t2, t3).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testEmpty3 = {
        val t1 = traversable.emptyOf[Int]
        val t = traversable.fromValues(t1).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testFlatMap = {
        val t = traversable.fromValues(1,2,3,4,5).flatMap(e => traversable.single(e))
        val a = traversable.fromValues(1,2,3,4,5)
        assertEquals(t, a)
        assertEquals(t, a)
    }
}
