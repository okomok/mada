

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class EqualsTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        type l = Int :: String :: java.lang.Integer :: Nil
        val l1: l = 3 :: "hello" :: new java.lang.Integer(10):: Nil
        val l2: l = 3 :: "hello" :: new java.lang.Integer(10):: Nil
        val l3 = 3 :: "helll" :: new java.lang.Integer(10):: Nil
        val l4 = 3 :: "hello" :: new java.lang.Integer(10):: 2.0 :: Nil

        assertEquals(l2, l1)
        AssertNotEquals(Nil, l1)
        assertEquals(Nil, Nil)
        AssertNotEquals(l3, l1)
        AssertNotEquals(l4, l1)
    }
}
