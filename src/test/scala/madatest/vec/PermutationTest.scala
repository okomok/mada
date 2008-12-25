

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector.arrayVector
import junit.framework.Assert._


class PermutationTest {
    def testTrivial: Unit = {
        val a = Vector.range(0, 6).copy
        val b = Vector.fromValues[Long](2,3,1,0,5,4) // shall be uniqued
        detail.TestVectorReadOnly(Array(2,3,1,0,5,4), a.permutation(b))
    }

    def testEmpty: Unit = {
        val a = Vector.range(0, 6).copy
        val b = Vector.fromValues[Long]()
        detail.TestEmpty(a.permutation(b))
    }
}
