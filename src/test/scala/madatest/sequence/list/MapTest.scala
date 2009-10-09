

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence.list
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        val t = list.Of(1,2,3)
        val u = list.Of(2,3,4)
        val k = t.map(_ + 1)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        val t = list.emptyOf[Int]
        val u = list.emptyOf[Int]
        val k = t.map(_ + 1)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testMapMap: Unit = {
        val t = list.Of(1,2,3)
        val u = list.Of(7,8,9)
        val k = t.map(_ + 1).map(_ + 2).map(_ + 3)

        assertEquals(u, k)
        assertEquals(u, k)
    }
}
