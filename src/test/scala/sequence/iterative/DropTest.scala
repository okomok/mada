

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class DropTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
    //    new NotStartable[Int].drop(5)
        val t = iterative.Of(4,5,1,3,2,9,7,10)
        val u = iterative.Of(9,7,10)
        val v = iterative.Of(10)
        val k = t.drop(5)
        assertEquals(u, k)
        assertEquals(u, k)
        val k_ = t.drop(7)
        assertEquals(v, k_)
        assertEquals(v, k_)
        assertTrue(t.drop(8).isEmpty)
        assertTrue(t.drop(9).isEmpty)
        assertTrue(t.drop(80).isEmpty)
    }

    def testWhile: Unit = {
        val t = iterative.Of(3,3,3,3,3,9,7,10)
        val u = iterative.Of(9,7,10)
        val v = iterative.Of(10)
        val k = t.dropWhile(_ == 3)
        assertEquals(u, k)
        assertTrue(t.dropWhile(_ != 99).isEmpty)
        assertEquals(t, t.dropWhile(_ => false))
    }

    def testFusion: Unit = {
        val t = iterative.Of(3,3,3,3,3,9,7,10)
        val u = iterative.Of(9,7,10)
        val k = t.drop(2).drop(3)
        assertEquals(k, u)
    }

    def testEmpty: Unit = {
        val k = iterative.empty.of[Int].drop(3)
        assertTrue(k.isEmpty)
        assertTrue(k.isEmpty)
    }
}
