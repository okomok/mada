

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class UnzipTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial {
        val i = new java.lang.Integer(10)

        type l1 = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil
        val l1: l1 = Box(3) :: Box("hello") :: Box(i) :: Nil

        type l2 = Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l2: l2 = Box(i) :: Box('a') :: Box(12) :: Nil

        type z = l1#zip[l2]
        val z: z = l1.zip(l2)
        meta.assertSame[Tuple2[Box[Int], Box[java.lang.Integer]] :: Tuple2[Box[String], Box[Char]] :: Tuple2[Box[java.lang.Integer], Box[Int]] :: Nil, z]

        type k = z#unzip
        val k: k = z.unzip
        meta.assertSame[Tuple2[Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil, Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil], k]

        assertEquals(l1, k._1)
        assertEquals(l2, k._2)
    }

    def testNil {
        type l1 = Nil
        val l1: l1 = Nil

        type l2 = Nil
        val l2: l2 = Nil

        type z = l1#zip[l2]
        val z: z = l1.zip(l2)
        meta.assertSame[Nil, z]

        type k = z#unzip
        val k: k = z.unzip
        meta.assertSame[Tuple2[Nil, Nil], k]

        assertEquals(Tuple2(Nil, Nil), k)
    }
}