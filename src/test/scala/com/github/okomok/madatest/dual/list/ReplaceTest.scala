

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class ReplaceTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val _m: l#replace[_1N, Char] = l.replace(_1N, 'c')
        val m: Int :: Char :: java.lang.Integer :: Char :: Int :: Nil = _m
        val e: Char = m.nth(_1N)
        assertEquals('c', e)

        val A = 3 :: 'c' :: i :: 'a' :: 12 :: Nil
        assertEquals(A, m)
    }
}