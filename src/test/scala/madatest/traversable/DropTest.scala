

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class DropTest {
    def testTrivial: Unit = {
        val t = traversable.fromValues(4,5,1,3,2,9,7,10)
        val u = traversable.fromValues(9,7,10)
        val v = traversable.fromValues(10)
        assertEquals(t.drop(5), u)
        assertEquals(t.drop(5), u)
        assertEquals(t.drop(7), v)
        assertEquals(t.drop(7), v)
        assertTrue(t.drop(8).isEmpty)
        assertTrue(t.drop(8).isEmpty)
        assertTrue(t.drop(9).isEmpty)
        assertTrue(t.drop(9).isEmpty)
        assertTrue(t.drop(80).isEmpty)
        assertTrue(t.drop(80).isEmpty)
    }

    def testWhile: Unit = {
        val t = traversable.fromValues(3,3,3,3,3,9,7,10)
        val u = traversable.fromValues(9,7,10)
        val v = traversable.fromValues(10)
        assertEquals(t.dropWhile(_ == 3), u)
        assertEquals(t.dropWhile(_ == 3), u)
        assertTrue(t.dropWhile(_ != 99).isEmpty)
        assertTrue(t.dropWhile(_ != 99).isEmpty)
        assertEquals(t, t.dropWhile(_ => false))
        assertEquals(t, t.dropWhile(_ => false))
    }

    def testFusion: Unit = {
        val t = traversable.fromValues(3,3,3,3,3,9,7,10)
        val u = traversable.fromValues(9,7,10)
        assertEquals(t.drop(2).drop(3), u)
        assertEquals(t.drop(2).drop(3), u)
    }
}
