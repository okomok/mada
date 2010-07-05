

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._



class SliceTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testSlice {
        val i = new java.lang.Integer(10)

        type Lst1 = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val lst1: Lst1 = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil;

        type Lst2 = Box[String] :: Nil
        val lst2: Lst2 = Box("hello") :: Nil;

        type Lst3 = Nil
        val lst3: Lst3 = Nil;
        {
            val e: Lst1#slice[_0N, _2N] = lst1.slice(_0N, _2N)
            val e_ : Box[Int]::Box[String]::Nil = e
            assertEquals(Box(3)::Box("hello")::Nil, e_)
        }

        {
            val e: Lst1#slice[_1N, _5N] = lst1.slice(_1N, _5N)
            val e_ : Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = e
            assertEquals(Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil, e_)
        }

        {
            val e: Lst1#slice[_2N, _4N] = lst1.slice(_2N, _4N)
            val e_ : Box[java.lang.Integer] :: Box[Char] :: Nil = e
            assertEquals(Box(i) :: Box('a') :: Nil, e_)
        }
        {
            val e: Lst2#slice[_0N, _0N] = lst2.slice(_0N, _0N)
            val e_ : Nil = e
            assertEquals(Nil, e_)
        }
        {
            val e: Lst2#slice[_0N, _1N] = lst2.slice(_0N, _1N)
            val e_ : Box[String] :: Nil = e
            assertEquals(Box("hello") :: Nil, e_)
        }

        {
            val e: Lst2#slice[_0N, _0N] = lst2.slice(_0N, _0N)
            val e_ : Nil = e
            assertEquals(Nil, e_)
        }
        ()
    }
}


object SliceTezt {
    import meta.{ assert, assertSame }

    trait testSlice {
        type lst = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil
        assertSame[Nil, lst#slice[_2N, _2N]]
        assertSame[Box[String] :: Box[Double] :: Box[Char] :: Nil, lst#slice[_1N, _4N]]
    }
}
