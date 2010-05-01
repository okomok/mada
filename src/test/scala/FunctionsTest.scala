

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import mada.function
import junit.framework.Assert._


class FunctionsTest {
    def testMemoize: Unit = {
        var i = 0
        def heavy(fixed: Int => Int, v: Int) = { i += 1; v * v }
        val ff: function.Transform[Int => Int] = (heavy _).curried
        val mf = function.memoize(ff)
        assertEquals(0, i)
        assertEquals(16, mf(4))
        assertEquals(16, mf(4))
        assertEquals(16, mf(4))
        assertEquals(1, i)
    }

    def testMemoizeFib: Unit = {
        val mf = function memoize { (f: Int => Int) => (x: Int) => if (x <= 1) 1 else f(x-1) + f(x-2) }
        assertEquals(1346269, mf(30))
    }
}
