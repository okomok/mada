

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}
import junit.framework.Assert._


class RangeTest {
    def testTrivial: Unit = {
        assertEquals(vector.range(2, 5), vector.range(2, 5))
//        assertEquals(vector.range(2L, 5L), vector.range(2L, 5L)) // rejected.
    }

    def testMe: Unit = {
        val ex = Array(5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        detail.TestVectorReadOnly(ex, vector.range(5, 19))
    }
}
