

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vectors
import junit.framework.Assert._


class WindowTest {
    def testTrivial = {
        val ac = Vectors.range(0, 10).window(1, 3)
        assertEquals(Vectors.range(1, 3), ac)
    }

    def testNontrivial = {
        val ac = Vectors.range(0, 10).window(3, 9).cut.window(-2, 5)
        assertEquals(Vectors.range(1, 8), ac)
    }

    def testFusion = {
        val ac = Vectors.range(0, 10).window(3, 9).window(-2, 5)
        assertEquals(Vectors.range(1, 8), ac)
    }
}
