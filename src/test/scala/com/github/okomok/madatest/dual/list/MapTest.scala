

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class MapTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class mkString extends Function1 {
        override  def apply[x <: Any](x: x): apply[x] = x.toString
        override type apply[x <: Any] = String
    }
    val mkString = new mkString

    def testTrivial {
        type xs = Box[Int] :: Box[String] :: Box[Char] :: Nil
        val xs: xs = Box(3) :: Box("hello") :: Box('a') :: Nil
        val u: xs#map[mkString] = xs.map(mkString)
        val v: Box[String] :: Box[String] :: Box[String] :: Nil = u
        assertEquals("3" :: "hello" :: "a" :: Nil, v)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#map[mkString] = xs.map(mkString)
        val v: Nil = u
        ()
    }
}

