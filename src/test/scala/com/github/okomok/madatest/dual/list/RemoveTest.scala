

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class RemoveTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)
    def testTrivial {
        val i = new java.lang.Integer(10)
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil

        val _m: l#remove[_1N] = l.remove(_1N)
        val m: Box[Int] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = _m
        val e: Char = m.nth(_2N).unbox
        assertEquals('a', e)
        ()
    }
}

