

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._



class SliceTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testSlice: Unit = {
        val i = new java.lang.Integer(10)

        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil;

        type Lst2 = String :: Nil
        val lst2: Lst2 = "hello" :: Nil;

        type Lst3 = Nil
        val lst3: Lst3 = Nil;
        {
            val e: Lst1#slice[_0N, _2N] = lst1.slice(_0N, _2N)
            val e_ : Int::String::Nil = e
            assertEquals(3::"hello"::Nil, e_)
        }

        {
            val e: Lst1#slice[_1N, _5N] = lst1.slice(_1N, _5N)
            val e_ : String :: java.lang.Integer :: Char :: Int :: Nil = e
            assertEquals("hello" :: i :: 'a' :: 12 :: Nil, e_)
        }

        {
            val e: Lst1#slice[_2N, _4N] = lst1.slice(_2N, _4N)
            val e_ : java.lang.Integer :: Char :: Nil = e
            assertEquals(i :: 'a' :: Nil, e_)
        }
        {
            val e: Lst2#slice[_0N, _0N] = lst2.slice(_0N, _0N)
            val e_ : Nil = e
            assertEquals(Nil, e_)
        }
        {
            val e: Lst2#slice[_0N, _1N] = lst2.slice(_0N, _1N)
            val e_ : String :: Nil = e
            assertEquals("hello" :: Nil, e_)
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
        type lst = Int :: String :: Double :: Char :: Float :: Nil
        assertSame[Nil, lst#slice[_2N, _2N]]
        assertSame[String :: Double :: Char :: Nil, lst#slice[_1N, _4N]]
    }
}
