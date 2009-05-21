

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        new NotStartable[Int].map(_ + 1)
        val t = sequence.Of(1,2,3)
        val u = sequence.Of(2,3,4)
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
        val t = sequence.Of(1,2,3)
        val u = sequence.Of(7,8,9)
        val k = t.map(_ + 1).map(_ + 2).map(_ + 3)

        import sequence.Map
        k match {
            case Map(Map(s, f), g) => fail
            case _ => ()
        }

        assertEquals(u, k)
        assertEquals(u, k)
    }
}
