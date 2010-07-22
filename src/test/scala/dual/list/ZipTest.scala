

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class ZipTest extends org.scalatest.junit.JUnit3Suite {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial {
        val i = new java.lang.Integer(10)
        type l1 = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil
        val l1: l1 = Box(3) :: Box("hello") :: Box(i) :: Nil

        type l2 = Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l2: l2 = Box(i) :: Box('a') :: Box(12) :: Nil

        val _z: l1#zip[l2] = l1.zip(l2)
        val z: Tuple2[Box[Int], Box[java.lang.Integer]] :: Tuple2[Box[String], Box[Char]] :: Tuple2[Box[java.lang.Integer], Box[Int]] :: Nil = _z

        val a = Tuple2(Box(3), Box(i)) :: Tuple2(Box("hello"), Box('a')) :: Tuple2(Box(i), Box(12)) :: Nil
        assertEquals(a, z)
    }

    def testNil {
        type l1 = Nil
        val l1: l1 = Nil

        type l2 = Nil
        val l2: l2 = Nil

        val _z: l1#zip[l2] = l1.zip(l2)
        val z: Nil = _z

        val a = Nil
        assertEquals(a, z)
    }

    def testLonger {
        val i = new java.lang.Integer(10)
        type l1 = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil
        val l1: l1 = Box(3) :: Box("hello") :: Box(i) :: Nil

        type l2 = Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Box[String] :: Nil
        val l2: l2 = Box(i) :: Box('a') :: Box(12) :: Box("ignored") :: Nil

        val _z: l1#zip[l2] = l1.zip(l2)
        val z: Tuple2[Box[Int], Box[java.lang.Integer]] :: Tuple2[Box[String], Box[Char]] :: Tuple2[Box[java.lang.Integer], Box[Int]] :: Nil = _z

        val a = Tuple2(Box(3), Box(i)) :: Tuple2(Box("hello"), Box('a')) :: Tuple2(Box(i), Box(12)) :: Nil
        assertEquals(a, z)
    }
}