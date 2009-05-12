

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.Vector
import junit.framework.Assert._


class RangeTest {
    def testTrivial = {
        assertEquals(Vector.range(2, 5), Vector.range(2, 5))
//        assertEquals(Vector.range(2L, 5L), Vector.range(2L, 5L)) // rejected.
    }

    def testMe {
        val ex = Array(5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        detail.TestVectorReadOnly(ex, Vector.range(5, 19))
    }
}
