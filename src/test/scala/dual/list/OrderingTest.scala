

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import mada.dual.{assert => dassert}
import nat.peano.Literal._
import junit.framework.Assert._


class OrderingTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil

        type ys    = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val ys: ys = _5 :: _6 :: _7 :: _8 :: _9 :: Nil

        meta.assert[list.ord[nat.ord]#equiv[xs, ys]]
        dassert(list.ord(nat.ord).equiv(xs, ys))
    }

    def testTrivial2 {
        type xs    = Nil
        val xs: xs = Nil

        type ys    = Nil
        val ys: ys = Nil

        meta.assert[list.ord[nat.ord]#equiv[xs, ys]]
        dassert(list.ord(nat.ord).equiv(xs, ys))
    }

    def testNilYS {
        type xs    = Nil
        val xs: xs = Nil

        type ys    = _5 :: _6 :: Nil
        val ys: ys = _5 :: _6 :: Nil

        meta.assert[list.ord[nat.ord]#lt[xs, ys]]
        dassert(list.ord(nat.ord).lt(xs, ys))
    }

    def testXSNil {
        type xs    = _7 :: _8 :: Nil
        val xs: xs = _7 :: _8 :: Nil

        type ys    = Nil
        val ys: ys = Nil

        meta.assert[list.ord[nat.ord]#gt[xs, ys]]
        dassert(list.ord(nat.ord).gt(xs, ys))
    }

    def testLonger {
        type xs    = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil

        type ys    = _5 :: _6 :: _7 :: Nil
        val ys: ys = _5 :: _6 :: _7 :: Nil

        meta.assert[list.ord[nat.ord]#gt[xs, ys]]
        dassert(list.ord(nat.ord).gt(xs, ys))
    }

    def testShorter {
        type xs    = _5 :: _6 :: _7 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: Nil

        type ys    = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val ys: ys = _5 :: _6 :: _7 :: _8 :: _9 :: Nil

        meta.assert[list.ord[nat.ord]#lt[xs, ys]]
        dassert(list.ord(nat.ord).lt(xs, ys))
    }

    def testComplicated {
        type xs    = _5 :: _7 :: _7 :: Nil
        val xs: xs = _5 :: _7 :: _7 :: Nil

        type ys    = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val ys: ys = _5 :: _6 :: _7 :: _8 :: _9 :: Nil

        meta.assert[list.ord[nat.ord]#gt[xs, ys]]
        dassert(list.ord(nat.ord).gt(xs, ys))
    }

    def testComplicated2 {
        type xs    = _5 :: _7 :: _7 :: Nil
        val xs: xs = _5 :: _7 :: _7 :: Nil

        type ys    = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val ys: ys = _5 :: _6 :: _7 :: _8 :: _9 :: Nil

        meta.assert[list.ord[nat.ord]#lt[ys, xs]]
        dassert(list.ord(nat.ord).lt(ys, xs))
    }

}
