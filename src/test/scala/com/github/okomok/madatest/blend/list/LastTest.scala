

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta


class LastTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    type lastOf[l <: List] = l#last
    meta.assertSame[Int, lastOf[Int :: String :: java.lang.Integer :: Char :: Int :: Nil]]

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val e: Lst1#last = lst1.last
        val e_ : Int = e
        assertEquals(12, e_)

        type Lst2 = String :: Nil
        val lst2: Lst2 = "hello" :: Nil
        val e2: Lst2#last = lst2.last
        val e2_ : String = e2
        assertEquals("hello", e2_)
    }
}

/*

class LastOrElseTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val e: Lst1#lastOrElse[Null] = lst1.lastOrElse(null)
        val e_ : Int = e
        assertEquals(12, e_)

        type Lst2 = String :: Nil
        val lst2: Lst2 = "hello" :: Nil
        val e2: Lst2#lastOrElse[Null] = lst2.lastOrElse(null)
        val e2_ : String = e2
        assertEquals("hello", e2_)
    }

    def testEmpty: Unit = {
        val i = new java.lang.Integer(10)
        type Lst1 = Nil
        val lst1: Lst1 = Nil

        val e: Lst1#lastOrElse[Int] = lst1.lastOrElse(12)
        val e_ : Int = e
        assertEquals(12, e_)
    }
}

*/
