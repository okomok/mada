

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada._
import junit.framework.Assert._


class UnsplitTest {
    def testTrivial: Unit = {
        val v1 = vector.fromArray(Array(0,1,2))
        val v2 = vector.fromArray(Array(3,4))
        val v3 = vector.fromArray(detail.Example.empty1)
        val v4 = vector.fromArray(Array(5,6))
        val v5 = vector.fromArray(Array(7,8,9,10))
        val v = vector.unsplit(vector.fromArray(Array(v1, v2, v3, v4, v5)).toIterable)(vector.fromValues(99))
        assertEquals(vector.fromValues(0,1,2,99,3,4,99,99,5,6,99,7,8,9,10), v)
    }

    def testOne: Unit = {
        val v = vector.unsplit(Vector(vector.from("abc")).toIterable)(vector.fromValues('X'))
        assertEquals(vector.from("abc"), v)
    }

    def testEmpty: Unit = {
        val v = vector.unsplit(vector.fromArray(Array[Vector[Int]]()).toIterable)(vector.fromValues(99))
        detail.TestEmpty(v)
    }
}
