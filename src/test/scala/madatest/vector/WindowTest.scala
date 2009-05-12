

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}
import junit.framework.Assert._


class WindowTest {
    def testTrivial = {
        val ac = vector.range(0, 10).window(1, 3)
        assertEquals(vector.range(1, 3), ac)
    }

    def testNontrivial = {
        val ac = vector.range(0, 10).window(3, 9).seal.window(-2, 5)
        assertEquals(vector.range(1, 8), ac)
    }

    def testFusion = {
        val ac = vector.range(0, 10).window(3, 9).window(-2, 5)
        assertEquals(vector.range(1, 8), ac)
    }
}
