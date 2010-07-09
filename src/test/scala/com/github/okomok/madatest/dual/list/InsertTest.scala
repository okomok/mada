

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._


class InsertTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)
    def testTrivial {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_1, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_1, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[Int] :: Box[String] :: Box[scala.Tuple1[Int]] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val a = Box(3) :: Box("wow") :: Box(scala.Tuple1(10)) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(a, m)
    }
    def testNil {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_1, Nil] = l.insert(_1, Nil)
        val m: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val a = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(a, m)
    }

    def testNilNil {
        type l = Nil
        val l: l = Nil

        val _m: l#insert[_0, Nil] = l.insert(_0, Nil)
        val m: Nil = _m
        val a = Nil
        assertEquals(a, m)
    }

    def testNilNotNil {
        type l = Nil
        val l: l = Nil

        val _m: l#insert[_0, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_0, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[String] :: Box[scala.Tuple1[Int]] :: Nil = _m
        val a = Box("wow") :: Box(scala.Tuple1(10)) :: Nil
        assertEquals(a, m)
    }

    def testHead {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_0, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_0, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[String] :: Box[scala.Tuple1[Int]] :: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val a = Box("wow") :: Box(scala.Tuple1(10)) :: Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(a, m)
    }

    def testTail {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_5, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_5, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Box[String] :: Box[scala.Tuple1[Int]] :: Nil = _m
        val a = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Box("wow") :: Box(scala.Tuple1(10)) :: Nil
        assertEquals(a, m)
    }
}
