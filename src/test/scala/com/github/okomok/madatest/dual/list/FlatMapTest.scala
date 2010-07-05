

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class FlatMapTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class oops extends Function1_Any_List {
        override  def apply[x <: Any](x: x): apply[x] = x :: "oops" :: Nil
        override type apply[x <: Any] = x :: String :: Nil
    }
    val oops = new oops

    def testTrivial {
        type xs = Int :: String :: Char :: Nil
        val xs: xs = 3 :: "hello" :: 'a' :: Nil
        val u: xs#flatMap[oops] = xs.flatMap(oops)
        val v: Int :: String :: String :: String :: Char :: String :: Nil = u
        assertEquals(3 :: "oops" :: "hello" :: "oops" :: 'a' :: "oops"  :: Nil, v)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#flatMap[oops] = xs.flatMap(oops)
        val v: Nil = u
        ()
    }
}
