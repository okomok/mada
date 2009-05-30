

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class FilterTest {
    def testTrivial: Unit = {
        new NotStartable[Int].filter(_ => false)
        val t = iterative.Of(1,2,2,3,4,5,5,6,7,8,9)
        val u = iterative.Of(2,2,4,6,8)
        val k = t.filter(_ % 2 == 0)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testTrivial2: Unit = {
        val t = iterative.Of(2,2,3,4,5,5,6,7,8,9)
        val u = iterative.Of(2,2,4,6,8)
        val k = t.filter(_ % 2 == 0)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        val t = iterative.emptyOf[Int]
        val k = t.filter(_ % 2 == 0)
        assertTrue(k.isEmpty)
        assertTrue(k.isEmpty)
    }

    def testEmpty2: Unit = {
        val t = iterative.Of(1,2,2,3,4,5,5,6,7,8,9)
        val k = t.filter(_ % 20 == 0)
        assertTrue(k.isEmpty)
        assertTrue(k.isEmpty)
    }

    def testFusion: Unit = {
        val t = iterative.Of(1,2,2,3,4,5,5,6,7,8,9)
        val u = iterative.Of(4,8)
        val k = t.filter(_ % 2 == 0).filter(_ % 4 == 0)
        assertEquals(u, k)
        assertEquals(u, k)
    }
}
