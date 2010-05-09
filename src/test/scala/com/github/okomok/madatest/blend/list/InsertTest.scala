

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class InsertTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val _m: l#insert[_1N, String :: Tuple1[Int] :: Nil] = l.insert[_1N]("wow" :: Tuple1(10) :: Nil)
        val m: Int :: String :: Tuple1[Int] :: String :: java.lang.Integer :: Char :: Int :: Nil = _m
        val a = 3 :: "wow" :: Tuple1(10) :: "hello" :: i :: 'a' :: 12 :: Nil
        assertEquals(a, m)
    }

    def testNil: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val _m: l#insert[_1N, Nil] = l.insert[_1N](Nil)
        val m: Int :: String :: java.lang.Integer :: Char :: Int :: Nil = _m
        val a = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        assertEquals(a, m)
    }

    def testNilNil: Unit = {
        type l = Nil
        val l: l = Nil

        val _m: l#insert[_0N, Nil] = l.insert[_0N](Nil)
        val m: Nil = _m
        val a = Nil
        assertEquals(a, m)
    }

    def testNilNotNil: Unit = {
        type l = Nil
        val l: l = Nil

        val _m: l#insert[_0N, String :: Tuple1[Int] :: Nil] = l.insert[_0N]("wow" :: Tuple1(10) :: Nil)
        val m: String :: Tuple1[Int] :: Nil = _m
        val a = "wow" :: Tuple1(10) :: Nil
        assertEquals(a, m)
    }

    def testHead: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val _m: l#insert[_0N, String :: Tuple1[Int] :: Nil] = l.insert[_0N]("wow" :: Tuple1(10) :: Nil)
        val m: String :: Tuple1[Int] :: Int :: String :: java.lang.Integer :: Char :: Int :: Nil = _m
        val a = "wow" :: Tuple1(10) :: 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        assertEquals(a, m)
    }

    def testTail: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val _m: l#insert[_5N, String :: Tuple1[Int] :: Nil] = l.insert[_5N]("wow" :: Tuple1(10) :: Nil)
        val m: Int :: String :: java.lang.Integer :: Char :: Int :: String :: Tuple1[Int] :: Nil = _m
        val a = 3 :: "hello" :: i :: 'a' :: 12 :: "wow" :: Tuple1(10) :: Nil
        assertEquals(a, m)
    }
}
