

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vectors
import mada.Vectors.fromArray
import junit.framework.Assert._


class ValuesTest {
    def testTrivial() = {
        val ex = Array(5,1,3,6,9,7,10,0)
        val ac = Vectors.fromValues(5,1,3,6,9,7,10,0)
        detail.TestVectorReadOnly(ex, ac)
    }

    def testEmpty() = {
        val ac = Vectors.fromValues[Int]()
        detail.TestEmpty(ac)
    }
}
