

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Functions
import junit.framework.Assert._


class FunctionsTest {
    def testMemoize: Unit = {
        var i = 0
        def heavy(fixed: Int => Int, v: Int) = { i += 1; v * v }
        val ff: Functions.Transform[Int => Int] = (heavy _).curry
        val mf = Functions.memoize(ff)
        assertEquals(0, i)
        assertEquals(16, mf(4))
        assertEquals(16, mf(4))
        assertEquals(16, mf(4))
        assertEquals(1, i)
    }
}
