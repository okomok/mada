

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import mada.Vectors.fromArray
import junit.framework.Assert._
import detail.Example._


class ForTest {
    def testTrivial: Unit = {
        val v = Vectors.fromArray(example1)
        val w = for (e <- v) yield e
        assertEquals(v, w)
    }

    def testFilter: Unit = {
        val v = Vectors.fromArray(example1)
        val w = for (e <- v; if (e < 999)) yield e
        assertEquals(v, w)
    }

    def testForeach: Unit = {
        val v = Vectors.fromArray(example1)
        val a = new java.util.ArrayList[Int]
        for (e <- v) a.add(e)
        assertEquals(Vectors.fromJclList(a), v)
    }

    def testFlatMap: Unit = {
        val v = Vectors.fromValues(1,2,3)
        val w = Vectors.fromValues(4)
        val x = for (e <- v; s <- w) yield e + s
        assertEquals(5+6+7, x.foldLeft(0)((_: Int) + (_: Int)))
    }
}
