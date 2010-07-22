

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class PartitionTest extends org.scalatest.junit.JUnit3Suite {

    case class Lt8() extends Function1 {
        override  def self = this
        override type self = Lt8
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat < _8
        override type apply[x <: Any] = x#asInstanceOfNat# <[_8]
    }

    def testTrivial {
        type xs = _9 :: _2 :: _6 :: _10 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _9 :: _2 :: _6 :: _10 :: _7 :: _8 :: _9 :: Nil
        type u = xs#partition[Lt8]
        val u: u = xs.partition(Lt8())
        meta.assertSame[Tuple2[_2 :: _6 :: _7 :: Nil, _9 :: _10 :: _8 :: _9 :: Nil], u]
        assertEquals(Tuple2(_2 :: _6 :: _7 :: Nil,  _9 :: _10 :: _8 :: _9 :: Nil), u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type u = xs#partition[Lt8]
        val u: u = xs.partition(Lt8())
        meta.assertSame[Tuple2[Nil, Nil], u]
        assertEquals(Tuple2(Nil, Nil), u)
    }

    def testTakeAll {
        type xs = _7 :: _4 :: _5 :: _6 :: _7 :: Nil
        val xs: xs = _7 :: _4 :: _5 :: _6 :: _7 :: Nil
        type u = xs#partition[Lt8]
        val u: u = xs.partition(Lt8())
        meta.assertSame[Tuple2[xs, Nil], u]
        assertEquals(Tuple2(xs, Nil), u)
    }

    def testTakeNothing {
        type xs = _9 :: _8 :: _10 :: _8 :: Nil
        val xs: xs = _9 :: _8 :: _10 :: _8 :: Nil
        type u = xs#partition[Lt8]
        val u: u = xs.partition(Lt8())
        meta.assertSame[Tuple2[Nil, xs], u]
        assertEquals(Tuple2(Nil, xs), u)
    }

}