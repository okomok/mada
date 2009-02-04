

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class UntokenizeTest {
    def testTrivial: Unit = {
        val v1 = Vectors.fromArray(Array(0,1,2))
        val v2 = Vectors.fromArray(Array(3,4))
        val v3 = Vectors.fromArray(detail.Example.empty1)
        val v4 = Vectors.fromArray(Array(5,6))
        val v5 = Vectors.fromArray(Array(7,8,9,10))
        val v = Vectors.untokenize(Vectors.fromArray(Array(v1, v2, v3, v4, v5)), Vectors.fromValues(99))
        assertEquals(Vectors.fromValues(99,0,1,2,99,3,4,99,99,5,6,99,7,8,9,10), v)
    }

    def testEmpty: Unit = {
        val v = Vectors.untokenize(Vectors.fromArray(Array[Vector[Int]]()), Vectors.fromValues(99))
        detail.TestEmpty(v)
    }
}
