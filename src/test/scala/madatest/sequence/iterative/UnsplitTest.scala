

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


import mada.sequence._
import junit.framework.Assert._


class UnsplitTest {
    def testTrivial: Unit = {
        val v1 = iterative.from(Array(0,1,2))
        val v2 = iterative.from(Array(3,4))
        val v3 = iterative.emptyOf[Int]
        val v4 = iterative.from(Array(5,6))
        val v5 = iterative.from(Array(7,8,9,10))
        val v = iterative.from(Array[iterative.Sequence[Int]](v1, v2, v3, v4, v5)).unsplit(iterative.Of(99))
        assertEquals(iterative.Of(0,1,2,99,3,4,99,99,5,6,99,7,8,9,10), v)
    }

    def testTrivial2: Unit = {
        val v1 = iterative.from(Array(0,1,2))
        val v2 = vector.from(Array(3,4))
        val v3 = iterative.empty
        val v4 = vector.from(Array(5,6))
        val v5 = iterative.from(Array(7,8,9,10))
        val v = iterative.from(Array[iterative.Sequence[Int]](v1, v2, v3, v4, v5)).unsplit(vector.Of(99))
        assertEquals(iterative.Of(0,1,2,99,3,4,99,99,5,6,99,7,8,9,10), v)
    }

    def testOne: Unit = {
        val v = iterative.Of(iterative.from("abc")).unsplit(iterative.Of('X'))
        assertEquals(iterative.from("abc"), v)
    }

    def testEmpty: Unit = {
        val v = iterative.from(Array[Vector[Int]]()).unsplit(iterative.Of(99))
        assertTrue(v.isEmpty)
    }
}
