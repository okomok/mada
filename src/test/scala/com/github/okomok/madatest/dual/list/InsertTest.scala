

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class InsertTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)
    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_1N, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_1N, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[Int] :: Box[String] :: Box[scala.Tuple1[Int]] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val a = Box(3) :: Box("wow") :: Box(scala.Tuple1(10)) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(a, m)
    }
    def testNil: Unit = {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_1N, Nil] = l.insert(_1N, Nil)
        val m: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val a = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(a, m)
    }

    def testNilNil: Unit = {
        type l = Nil
        val l: l = Nil

        val _m: l#insert[_0N, Nil] = l.insert(_0N, Nil)
        val m: Nil = _m
        val a = Nil
        assertEquals(a, m)
    }

    def testNilNotNil: Unit = {
        type l = Nil
        val l: l = Nil

        val _m: l#insert[_0N, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_0N, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[String] :: Box[scala.Tuple1[Int]] :: Nil = _m
        val a = Box("wow") :: Box(scala.Tuple1(10)) :: Nil
        assertEquals(a, m)
    }

    def testHead: Unit = {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_0N, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_0N, Box("wow") :: Box(scala.Tuple1(10)) :: Nil)
        val m: Box[String] :: Box[scala.Tuple1[Int]] :: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val a = Box("wow") :: Box(scala.Tuple1(10)) :: Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(a, m)
    }

    def testTail: Unit = {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#insert[_5N, Box[String] :: Box[scala.Tuple1[Int]] :: Nil] = l.insert(_5N, "wow" :: Tuple1(10) :: Nil)
        val m: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Box[String] :: Box[scala.Tuple1[Int]] :: Nil = _m
        val a = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Box("wow") :: Box(scala.Tuple1(10)) :: Nil
        assertEquals(a, m)
    }
}
