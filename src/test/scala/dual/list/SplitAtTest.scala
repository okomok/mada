

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class SplitAtTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        type u = xs#splitAt[_3]
        val u: u = xs.splitAt(_3)
        meta.assertSame[Tuple2[_5 :: _6 :: _7 :: Nil, _8 :: _9 :: Nil], u]
        assertEquals(Tuple2(_5 :: _6 :: _7 :: Nil, _8 :: _9 :: Nil), u)
    }

    def testTakeAll {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        type u = xs#splitAt[_20]
        val u: u = xs.splitAt(_20)
        meta.assertSame[Tuple2[_5 :: _6 :: _7 :: _8 :: _9 :: Nil, Nil], u]
        assertEquals(Tuple2(_5 :: _6 :: _7 :: _8 :: _9 :: Nil, Nil), u)
    }

    def testTakeNothing {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        type u = xs#splitAt[_0]
        val u: u = xs.splitAt(_0)
        meta.assertSame[Tuple2[Nil, _5 :: _6 :: _7 :: _8 :: _9 :: Nil], u]
        assertEquals(Tuple2(Nil, _5 :: _6 :: _7 :: _8 :: _9 :: Nil), u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#splitAt[_3]
        val u: u = xs.splitAt(_3)
        meta.assertSame[Tuple2[Nil, Nil], u]
        assertEquals(Tuple2(Nil, Nil), u)
    }

}
