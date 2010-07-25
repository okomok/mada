

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class CountTest extends org.scalatest.junit.JUnit3Suite {

    case class Lt8() extends Function1 {
        override type self = Lt8
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat < _8
        override type apply[x <: Any] = x#asInstanceOfNat# <[_8]
    }

    def testTrivial {
        type xs = _5 :: _6 :: _10 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _10 :: _7 :: _8 :: _9 :: Nil
        val u: xs#count[Lt8] = xs.count(Lt8())
        meta.assertSame[_3, xs#count[Lt8]]
        assertEquals(_3, u)
    }

    def testTrivialToZero {
        type xs = _9 :: _10 :: _8 :: _9 :: Nil
        val xs: xs = _9 :: _10 :: _8 :: _9 :: Nil
        val u: xs#count[Lt8] = xs.count(Lt8())
        meta.assertSame[_0, xs#count[Lt8]]
        assertEquals(_0, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#count[Lt8] = xs.count(Lt8())
        meta.assertSame[_0, xs#count[Lt8]]
        assertEquals(_0, u)
    }

}
