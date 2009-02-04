

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vectors
import junit.framework.Assert._


class RangeTest {
    def testTrivial = {
        assertEquals(Vectors.range(2, 5), Vectors.range(2, 5))
//        assertEquals(Vectors.range(2L, 5L), Vectors.range(2L, 5L)) // rejected.
    }

    def testMe {
        val ex = Array(5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        detail.TestVectorReadOnly(ex, Vectors.range(5, 19))
    }
}
