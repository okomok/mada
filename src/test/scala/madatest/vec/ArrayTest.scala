

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector.arrayVector
import junit.framework.Assert._


class ArrayTest {
    def testTrivial() = {
        val a = arrayVector(Array.range(0, 6))
        val b = arrayVector(Array.range(0, 6))
        assertEquals(a, b)
    }

    def testWritable() = {
        val a = Array.range(0, 6)
        a(0) = 99
        val v2 = arrayVector(Array.range(0, 6))
        v2(0) = 99
        assertEquals(arrayVector(a), v2)
    }

    def testMe {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        assertNotSame(ex, ac)
        detail.TestVectorReadWrite(ex, arrayVector(ac))
    }
}
