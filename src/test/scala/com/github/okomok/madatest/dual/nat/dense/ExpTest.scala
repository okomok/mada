

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.Dense
import junit.framework.Assert._


class ExpTest extends junit.framework.TestCase {

    def testTrivial {
        assertEquals(_9, _3 ^ _2)
        assertEquals(_8, _2 ^ _3)
        assertEquals(_1, _3 ^ _0)
        assertEquals(_1, _0 ^ _0)
        assertEquals(_0, _0 ^ _9)
        assertEquals(_6 ** _6, _6 ^ _2)
        assertEquals(_15 + _1, _4 ^ _2)
        assertEquals(_1, _1 ^ _1)
    }

    def testBig {
        assertEquals(32768, (_2 ^ _15).undual)
        assertEquals(1073741824, (_8 ^ _10).undual)
    }

    def testMultiplyDuality {
        val a: _4# ^ [_2] = _4 ^ _2
        assertEquals(_15 + _1, a)
        /*
        hmm...
        val b: _8 = a
        assert(a === b)
        assert(a === _8)
        */
    }

    trait testTrivial {
        //meta.assertSame[_9, _3# ^[_2]]
    }

}
