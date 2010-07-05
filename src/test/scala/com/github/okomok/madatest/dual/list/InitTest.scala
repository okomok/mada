

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class InitTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)


    trait testMeta {
        type initInit[l <: List] = l#init#init

        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        meta.assertSame[Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil, initInit[l]]
    }

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val li: l#init = l.init
        val lii: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Nil = li
        val A = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Nil
        assertEquals(A, lii)
    }

    def testTrivial2: Unit = {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Nil

        val li: l#init = l.init
        val lii: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil = li
        val A = Box(3) :: Box("hello") :: Box(i) :: Nil
        assertEquals(A, lii)
    }

    def testOne: Unit = {
        type l = Box[Int] :: Nil
        val l: l = Box(12) :: Nil

        val li: l#init = l.init
        val lii: Nil = li
        assertSame(Nil, lii)
    }
}
