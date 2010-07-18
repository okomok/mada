

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class SortTest extends junit.framework.TestCase {

    def testTrivial {
        type xs = _9 :: _2 :: _6 :: _10 :: _7 :: _9 :: _8 :: Nil
        val xs: xs = _9 :: _2 :: _6 :: _10 :: _7 :: _9 :: _8 :: Nil
        type u = xs#sort[ordering.ofNat]
        val u: u = xs.sort(ordering.ofNat)
        meta.assertSame[_2 :: _6 :: _7 :: _8 :: _9 :: _9 :: _10 :: Nil, u]
        assertEquals(_2 :: _6 :: _7 :: _8 :: _9 :: _9 :: _10 :: Nil, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#sort[ordering.ofNat]
        val u: u = xs.sort(ordering.ofNat)
        meta.assertSame[Nil, u]
        assertEquals(Nil, u)
    }

}
