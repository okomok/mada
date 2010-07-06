

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class UndualTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val r: l#undual = l.undual
        val k: mada.sequence.List[scala.Any] = r
        assertEquals(3 :: "hello" :: i :: 'a' :: 12 :: mada.sequence.Nil.of[scala.Any], k)
        ()
    }

    def testNil {
        type l = Nil
        val l: l = Nil
        val r: l#undual = l.undual
        val k: mada.sequence.List[scala.Any] = r
        assertEquals(mada.sequence.Nil, k)
        ()
    }
}

