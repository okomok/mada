

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector.arrayVector
import junit.framework.Assert._
import madatest.vec.detail.Example._


class AppendTest {
    def testTrivial {
        val actual = arrayVector(Array(0,18,14,17)) ++ arrayVector(Array(19, 8,13, 6, 4,23, 0,12,15,11, 4))
        detail.TestVectorReadWrite(example1, actual)
    }

    def testNontrivial {
        val actual = arrayVector(Array(0,18,14,17)) ++
            arrayVector(Array(19, 8,13, 6, 4)) ++ arrayVector(empty1) ++
            arrayVector(Array(23, 0,12,15)) ++ arrayVector(empty1) ++
            arrayVector(Array(11, 4)) ++ arrayVector(empty1) ++ arrayVector(empty1)
        detail.TestVectorReadWrite(example1, actual)
    }

    def testEmpty {
        detail.TestEmpty(arrayVector(empty1) ++ arrayVector(empty1))
        detail.TestEmpty(arrayVector(empty1) ++ arrayVector(empty1) ++ arrayVector(empty1))
    }
}
