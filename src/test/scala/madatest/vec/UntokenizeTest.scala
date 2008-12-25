

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class UntokenizeTest {
    def testTrivial: Unit = {
        val v1 = Vector.arrayVector(Array(0,1,2))
        val v2 = Vector.arrayVector(Array(3,4))
        val v3 = Vector.arrayVector(detail.Example.empty1)
        val v4 = Vector.arrayVector(Array(5,6))
        val v5 = Vector.arrayVector(Array(7,8,9,10))
        val v = Vector.untokenize(Vector.arrayVector(Array(v1, v2, v3, v4, v5)), Vector.fromValues(99))
        assertEquals(Vector.fromValues(99,0,1,2,99,3,4,99,99,5,6,99,7,8,9,10), v)
    }

    def testEmpty: Unit = {
        val v = Vector.untokenize(Vector.arrayVector(Array[Vector[Int]]()), Vector.fromValues(99))
        detail.TestEmpty(v)
    }
}
