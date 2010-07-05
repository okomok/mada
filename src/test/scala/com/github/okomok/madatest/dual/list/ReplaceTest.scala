

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class ReplaceTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#replace[_1N, Box[Char]] = l.replace(_1N, Box('c'))
        val m: Box[Int] :: Box[Char] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val e: Char = m.nth(_1N).unbox
        assertEquals('c', e)

        val A = Box(3) :: Box('c') :: Box(i) :: Box('a') :: Box(12) :: Nil
        assertEquals(A, m)
    }
}
