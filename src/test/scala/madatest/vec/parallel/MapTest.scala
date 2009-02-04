

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada._
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        val v = Vectors.range(0, 10)
        val e = Vectors.range(1, 11)
        assertEquals(e, v.parallel.map(_ + 1))
        assertEquals(e, v.parallel(4).map(_ + 1))
        assertEquals(e, v.parallel(500).map(_ + 1))
    }

    def testMapMap: Unit = {
        val v = Vectors.range(0, 10)
        val e = Vectors.range(2, 12)
        for (i <- (0 until 3)) {
        assertEquals(e, v.parallel.map(_ + 1).parallel.map(_ + 1))
        assertEquals(e, v.parallel(4).map(_ + 1).parallel.map(_ + 1))
        assertEquals(e, v.parallel(500).map(_ + 1).parallel.map(_ + 1))
        ()
        }
    }

    def testFusion: Unit = {
        val v = Vectors.range(0, 10)
        val e = Vectors.range(2, 12)
        assertEquals(e, v.parallel.map(_ + 1).map(_ + 1))
        assertEquals(e, v.parallel(4).map(_ + 1).map(_ + 1))
        assertEquals(e, v.parallel(500).map(_ + 1).map(_ + 1))

        assertEquals(e.reduce(_ + _), v.parallel.map(_ + 1).map(_ + 1).reduce(_ + _))
        assertEquals(7, v.parallel.map(_ + 1).map(_ + 1).seek(_ == 7).get)
    }
}
