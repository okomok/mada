

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada._
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        val v = vector.range(0, 10)
        val e = vector.range(1, 11)
        assertEquals(e, v.map(_ + 1))
    }

    def testFusion: Unit = {
        val v = vector.range(0, 10)
        val e = vector.range(2, 12)
        assertEquals(e, v.map(_ + 1).map(_ + 1))
    }

    def testLoopFusion: Unit = {
        val a = new java.util.ArrayList[Int]
        vector.range(0, 10).map(_ + 1).foreach(a.add(_: Int))
        val e = vector.range(1, 11)
        assertEquals(e, vector.fromJclList(a))
    }
}
