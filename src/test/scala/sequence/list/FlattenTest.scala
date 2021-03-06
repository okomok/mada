

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence.list
import junit.framework.Assert._


class FlattenTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t1 = list.Of(0,1,2)
        val t2 = list.Of(3,4)
        val t3 = list.empty.of[Int]
        val t4 = list.Of(5,6)
        val t5 = list.Of(7,8,9,10)
        val t = list.Of(t1, t2, t3, t4, t5).flatten
        val a = list.Of(0,1,2,3,4,5,6,7,8,9,10)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty: Unit = {
        val t1 = list.empty.of[Int]
        val t2 = list.Of(3,4)
        val t3 = list.empty.of[Int]
        val t4 = list.empty.of[Int]
        val t = list.Of(t1, t2, t3, t4).flatten
        val a = list.Of(3,4)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty2: Unit = {
        val t1 = list.empty.of[Int]
        val t2 = list.empty.of[Int]
        val t3 = list.empty.of[Int]
        val t = list.Of(t1, t2, t3).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testEmpty3: Unit = {
        val t1 = list.empty.of[Int]
        val t = list.Of(t1).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testFlatMap: Unit = {
        val t = list.Of(1,2,3,4,5).flatMap(e => list.single(e))
        val a = list.Of(1,2,3,4,5)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testInfinite: Unit = {
        val L = list.repeat(list.Of(1,2,3,4,5)).flatten
        val A = list.Of(1,2,3,4,5,1,2,3,4,5,1,2,3)
        assertEquals(A, L.take(13))
    }
}
