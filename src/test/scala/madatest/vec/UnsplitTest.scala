

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class UnsplitTest {
    def testTrivial: Unit = {
        val v1 = Vector.fromArray(Array(0,1,2))
        val v2 = Vector.fromArray(Array(3,4))
        val v3 = Vector.fromArray(detail.Example.empty1)
        val v4 = Vector.fromArray(Array(5,6))
        val v5 = Vector.fromArray(Array(7,8,9,10))
        val v = Vector.unsplit(Vector.fromArray(Array(v1, v2, v3, v4, v5)).toIterable)(Vector.fromValues(99))
        assertEquals(Vector.fromValues(0,1,2,99,3,4,99,99,5,6,99,7,8,9,10), v)
    }

    def testOne: Unit = {
        val v = Vector.unsplit(Vector(Vector.from("abc")).toIterable)(Vector.fromValues('X'))
        assertEquals(Vector.from("abc"), v)
    }

    def testEmpty: Unit = {
        val v = Vector.unsplit(Vector.fromArray(Array[Vector[Int]]()).toIterable)(Vector.fromValues(99))
        detail.TestEmpty(v)
    }
}
