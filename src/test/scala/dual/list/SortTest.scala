

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class SortTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs = _9 :: _2 :: _6 :: _10 :: _7 :: _9 :: _8 :: Nil
        val xs: xs = _9 :: _2 :: _6 :: _10 :: _7 :: _9 :: _8 :: Nil
        type u = xs#sort[nat.ord]
        val u: u = xs.sort(nat.ord)
        meta.assertSame[_2 :: _6 :: _7 :: _8 :: _9 :: _9 :: _10 :: Nil, u#force]
        assertEquals(_2 :: _6 :: _7 :: _8 :: _9 :: _9 :: _10 :: Nil, u)
    }

    def testTrivialOne {
        type xs = _3 :: Nil
        val xs: xs = _3 :: Nil
        type u = xs#sort[nat.ord]
        val u: u = xs.sort(nat.ord)
        meta.assertSame[_3 :: Nil, u#force]
        assertEquals(_3 :: Nil, u)
    }

    def testTrivialTwo {
        type xs = _4 :: _3 :: Nil
        val xs: xs = _4 :: _3 :: Nil
        type u = xs#sort[nat.ord]
        val u: u = xs.sort(nat.ord)
        meta.assertSame[_3 :: _4 :: Nil, u#force]
        assertEquals(_3 :: _4 :: Nil, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#sort[nat.ord]
        val u: u = xs.sort(nat.ord)
        meta.assertSame[Nil, u#force]
        assertEquals(Nil, u)
    }

}
