

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class ForallTest extends junit.framework.TestCase {

    case class Gt3() extends Function1 {
        override  def self = this
        override type self = Gt3
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat > _3
        override type apply[x <: Any] = x#asInstanceOfNat# >[_3]
    }

    def testTrivial {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val u: xs#forall[Gt3] = xs.forall(Gt3())
        meta.assertSame[`true`, xs#forall[Gt3]]
        assertEquals(`true`, u)
    }

    def testTrivialFalse {
        type xs = _5 :: _6 :: _7 :: _1 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _1 :: _9 :: Nil
        val u: xs#forall[Gt3] = xs.forall(Gt3())
        meta.assertSame[`false`, xs#forall[Gt3]]
        assertEquals(`false`, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#forall[Gt3] = xs.forall(Gt3())
        meta.assertSame[`true`, xs#forall[Gt3]]
        assertEquals(`true`, u)
    }

}