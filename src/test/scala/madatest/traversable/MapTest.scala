

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        val t = traversable.fromValues(1,2,3)
        val u = traversable.fromValues(2,3,4)
        assertEquals(t.map(_ + 1), u)
        assertEquals(t.map(_ + 1), u)
    }

    def testEmpty: Unit = {
        val t = traversable.emptyOf[Int]
        val u = traversable.emptyOf[Int]
        assertEquals(t.map(_ + 1), u)
        assertEquals(t.map(_ + 1), u)
    }
}
