

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import junit.framework.Assert._


class OrderedTest {
    def testTrivial: Unit = {
        val v1 = vector.from("ab")
        val v2 = vector.from("")
        val v3 = vector.from("abc")
        val v4 = vector.from("aec")
        val v5 = vector.from("b")
        val v6 = vector.from("abd")
        val vv = Vector(v1, v2, v3, v4, v5, v6)
        vv.sort//(vector.orderedView[Char])
        val ww = Vector(v2, v1, v3, v6, v4, v5)
        assertEquals(ww, vv)
    }

    def testOrdering: Unit = {
        implicit val charComp1 = mada.compare.from{ (x: Char, y: Char) => x < y }.toOrdering
    //    implicit val charComp2 = mada.compare.toGetOrdered{ (x: Char, y: Char) => x < y }
        val v1 = vector.from("ab")
        val v2 = vector.from("")
        val v3 = vector.from("abc")
        val v4 = vector.from("aec")
        val v5 = vector.from("b")
        val v6 = vector.from("abd")
        val vv = Vector(v1, v2, v3, v4, v5, v6)
        sortByOrdering(vv)
        val ww = Vector(v2, v1, v3, v6, v4, v5)
        assertEquals(ww, vv)
    }

    def sortByOrdering[A](v: Vector[A])(implicit o: Ordering[A]): Unit = {
        v.sortBy(mada.compare.fromOrdering(o))
    }
}