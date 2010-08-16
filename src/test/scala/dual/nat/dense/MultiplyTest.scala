

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.Dense
import junit.framework.Assert._


class MultiplyTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        assertEquals(_6, _3 times _2)
        assertEquals(_6, _2 times _3)
        assertEquals(_0, _3 times _0)
        assertEquals(_0, _0 times _0)
        assertEquals(_0, _0 times _9)
        assertEquals(_12, _6 times _2)
        assertEquals(_15, _3 times _5)
        assertEquals(_8, _4 times _2)
        assertEquals(_1, _1 times _1)
        assertEquals(_9, _3 times _3)
    }

    def testDuality {
        val a: _4# times [_2] = _4 times _2
        assertEquals(_8, a)
        val b: _8 = a
        mada.dual.assert(a equal b)
        mada.dual.assert(a equal _8)
    }

    trait teztTrivial {
        free.assertSame[_6, _3 # times[ _2]]
        free.assertSame[_6, _2 # times[ _3]]
        free.assertSame[_0, _3 # times[ _0]]
        free.assertSame[_0, _0 # times[ _0]]
        free.assertSame[_0, _0 # times[ _9]]
        free.assertSame[_12, _6 # times[ _2]]
        free.assertSame[_15, _3 # times[ _5]]
        free.assertSame[_8, _4 # times[ _2]]
        free.assertSame[_1, _1 # times[ _1]]
        free.assertSame[_9, _3 # times[ _3]]
    }
}
