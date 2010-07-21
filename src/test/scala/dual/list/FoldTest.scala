

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class FoldTest extends org.scalatest.junit.JUnit3Suite {

    case class Plus() extends Function2 {
        override  def self = this
        override type self = Plus
        override  def apply[x <: Any, y <: Any](x: x, y: y): apply[x, y] = x.asInstanceOfNat + y.asInstanceOfNat
        override type apply[x <: Any, y <: Any] = x#asInstanceOfNat# +[y#asInstanceOfNat]
    }

    case class Sub() extends Function2 {
        override  def self = this
        override type self = Sub
        override  def apply[x <: Any, y <: Any](x: x, y: y): apply[x, y] = x.asInstanceOfNat - y.asInstanceOfNat
        override type apply[x <: Any, y <: Any] = x#asInstanceOfNat# -[y#asInstanceOfNat]
    }

    def testReduceLeft {
        type xs = _15 :: _1 :: _2 :: _3 :: _4 :: Nil
        val xs: xs = _15 :: _1 :: _2 :: _3 :: _4 :: Nil
        val u: xs#reduceLeft[Sub] = xs.reduceLeft(Sub())
        meta.assertSame[_5, xs#reduceLeft[Sub]]
        assertEquals(_5, u)
    }

    def testReduceRight {
        type xs = _1 :: _2 :: _3 :: _4 :: Nil
        val xs: xs = _1 :: _2 :: _3 :: _4 :: Nil
        val u: xs#reduceRight[Plus] = xs.reduceRight(Plus())
        meta.assertSame[_10, xs#reduceRight[Plus]]
        assertEquals(_10, u)
    }

    def testReduceRightRight {
        type xs = _15 :: _1 :: _2 :: _3 :: _4 :: Nil
        val xs: xs = _15 :: _1 :: _2 :: _3 :: _4 :: Nil
        try {
            val u: xs#reduceRight[Sub] = xs.reduceRight(Sub())
            fail("never come here")
        } catch {
            case _ =>
        }
        ()
    }

}
