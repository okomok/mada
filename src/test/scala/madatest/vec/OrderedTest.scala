

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import junit.framework.Assert._


class OrderedTest {
    def testTrivial: Unit = {
        val v1 = Vector.from("ab")
        val v2 = Vector.from("")
        val v3 = Vector.from("abc")
        val v4 = Vector.from("aec")
        val v5 = Vector.from("b")
        val v6 = Vector.from("abd")
        val vv = Vector(v1, v2, v3, v4, v5, v6)
        vv.sort//(Vector.orderedView[Char])
        val ww = Vector(v2, v1, v3, v6, v4, v5)
        assertEquals(ww, vv)
    }

    def testOrdering: Unit = {
        implicit val charComp1 = mada.compare.toOrdering{ (x: Char, y: Char) => x < y }
    //    implicit val charComp2 = mada.compare.toGetOrdered{ (x: Char, y: Char) => x < y }
        val v1 = Vector.from("ab")
        val v2 = Vector.from("")
        val v3 = Vector.from("abc")
        val v4 = Vector.from("aec")
        val v5 = Vector.from("b")
        val v6 = Vector.from("abd")
        val vv = Vector(v1, v2, v3, v4, v5, v6)
        sortByOrdering(vv)
        val ww = Vector(v2, v1, v3, v6, v4, v5)
        assertEquals(ww, vv)
    }

    def sortByOrdering[A](v: Vector[A])(implicit o: Ordering[A]): Unit = {
        v.sortBy(mada.compare.fromOrdering(o))
    }
}
