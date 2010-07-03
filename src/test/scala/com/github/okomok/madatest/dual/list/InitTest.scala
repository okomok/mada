

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

        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        meta.assertSame[Int :: String :: java.lang.Integer :: Nil, initInit[l]]
    }

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val li: l#init = l.init
        val lii: Int :: String :: java.lang.Integer :: Char :: Nil = li
        val A = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(A, lii)
    }

    def testTrivial2: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: Nil

        val li: l#init = l.init
        val lii: Int :: String :: java.lang.Integer :: Nil = li
        val A = 3 :: "hello" :: i :: Nil
        assertEquals(A, lii)
    }

    def testOne: Unit = {
        type l = Int :: Nil
        val l: l = 12 :: Nil

        val li: l#init = l.init
        val lii: Nil = li
        assertSame(Nil, lii)
    }
}