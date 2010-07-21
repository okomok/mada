

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._


class MapTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class mkString extends Function1 {
        override  def self = this
        override type self = mkString
        override  def apply[x <: Any](x: x): apply[x] = Box(x.undual.toString)
        override type apply[x <: Any] = Box[String]
    }
    val mkString = new mkString

    def testTrivial {
        type xs = Box[Int] :: Box[String] :: Box[Char] :: Nil
        val xs: xs = Box(3) :: Box("hello") :: Box('a') :: Nil
        val u: xs#map[mkString] = xs.map(mkString)
        val v: Box[String] :: Box[String] :: Box[String] :: Nil = u
        assertEquals(Box("3") :: Box("hello") :: Box("a") :: Nil, v)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#map[mkString] = xs.map(mkString)
        val v: Nil = u
        ()
    }
}


class MapNatTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class add2 extends Function1 {
        override  def self = this
        override type self = add2
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat + _2
        override type apply[x <: Any] = x#asInstanceOfNat# +[_2]
    }
    val add2 = new add2

    def testTrivial {
        type xs = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        val u: xs#map[add2] = xs.map(add2)
        val v: _5 :: _6 :: _7 :: _8 :: Nil = u
        assertEquals(_5 :: _6 :: _7 :: _8 :: Nil, v)
    }
}

