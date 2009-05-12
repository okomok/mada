

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}
import mada.vector.fromArray
import junit.framework.Assert._


class ValuesTest {
    def testTrivial() = {
        val ex = Array(5,1,3,6,9,7,10,0)
        val ac = vector.fromValues(5,1,3,6,9,7,10,0)
        detail.TestVectorReadOnly(ex, ac)
    }

    def testEmpty() = {
        val ac = vector.fromValues[Int]()
        detail.TestEmpty(ac)
    }
}
