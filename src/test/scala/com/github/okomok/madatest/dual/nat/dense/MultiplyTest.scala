

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.Dense
import junit.framework.Assert._


class MultiplyTest extends junit.framework.TestCase {

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

    def testMultiplyDuality {
        val a: _4# ** [_2] = _4 ** _2
        assertEquals(_8, a)
        /*
        hmm...
        val b: _8 = a
        assert(a === b)
        assert(a === _8)
        */
    }
/*
    hmm...
    trait testMultiply {
        meta.assert[_3# **[_2]# ===[_6]]
        meta.assert[_0# **[_3]# ===[_0]]
        meta.assert[_1# **[_3]# ===[_3]]
        meta.assert[_3# **[_1]# ===[_3]]
        meta.assert[_2# **[_3]# ===[_6]]
        meta.assert[_9# **[_1]# ===[_9]]
        meta.assert[_3# **[_3]# ===[_9]]
        meta.assert[_4# **[_2]# ===[_8]]
    }
    */
}
