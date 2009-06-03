

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence.{Vector, vector}
import mada.sequence.vector.fromArray
import junit.framework.Assert._


class ValuesTest {
    def testTrivial: Unit = {
        val ex = Array(5,1,3,6,9,7,10,0)
        val ac = vector.of(5,1,3,6,9,7,10,0)
        detail.TestVectorReadOnly(ex, ac)
    }

    def testEmpty: Unit = {
        val ac = vector.of[Int]()
        detail.TestEmpty(ac)
    }
}
