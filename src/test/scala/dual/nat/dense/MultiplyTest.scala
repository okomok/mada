

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
        assertEquals(_6, _3 ** _2)
        assertEquals(_6, _2 ** _3)
        assertEquals(_0, _3 ** _0)
        assertEquals(_0, _0 ** _0)
        assertEquals(_0, _0 ** _9)
        assertEquals(_12, _6 ** _2)
        assertEquals(_15, _3 ** _5)
        assertEquals(_8, _4 ** _2)
        assertEquals(_1, _1 ** _1)
        assertEquals(_9, _3 ** _3)
    }

    def testDuality {
        val a: _4# ** [_2] = _4 ** _2
        assertEquals(_8, a)
        val b: _8 = a
        mada.dual.assert(a === b)
        mada.dual.assert(a === _8)
    }

    trait teztTrivial {
        meta.assertSame[_6, _3 # **[ _2]]
        meta.assertSame[_6, _2 # **[ _3]]
        meta.assertSame[_0, _3 # **[ _0]]
        meta.assertSame[_0, _0 # **[ _0]]
        meta.assertSame[_0, _0 # **[ _9]]
        meta.assertSame[_12, _6 # **[ _2]]
        meta.assertSame[_15, _3 # **[ _5]]
        meta.assertSame[_8, _4 # **[ _2]]
        meta.assertSame[_1, _1 # **[ _1]]
        meta.assertSame[_9, _3 # **[ _3]]
    }
}
