

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import junit.framework.Assert._


class WindowTest {
    def testTrivial = {
        val ac = Vector.range(0, 10).window(1, 3)
        assertEquals(Vector.range(1, 3), ac)
    }

    def testNontrivial = {
        val ac = Vector.range(0, 10).window(3, 9).seal.window(-2, 5)
        assertEquals(Vector.range(1, 8), ac)
    }

    def testFusion = {
        val ac = Vector.range(0, 10).window(3, 9).window(-2, 5)
        assertEquals(Vector.range(1, 8), ac)
    }
}
