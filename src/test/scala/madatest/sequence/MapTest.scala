

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        new NotStartable[Int].map(_ + 1)
        val t = sequence.of(1,2,3)
        val u = sequence.of(2,3,4)
        val k = t.map(_ + 1)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        val t = sequence.emptyOf[Int]
        val u = sequence.emptyOf[Int]
        val k = t.map(_ + 1)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testFusion: Unit = {
        val t = sequence.of(1,2,3)
        val u = sequence.of(4,5,6)
        val k = t.map(_ + 1).map(_ + 2)
        assertEquals(u, k)
        assertEquals(u, k)
    }
}
