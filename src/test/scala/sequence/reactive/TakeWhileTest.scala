

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class TakeWhileTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial0: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 4).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4), vector.from(b))
    }

    def testAll: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 10).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4,5,6), vector.from(b))
    }

    def testEmpty: Unit = {
        val a = vector.empty[Int]
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 10).foreach(b.add(_))
        assertTrue(b.isEmpty)
    }

    def testNone: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ > 10).foreach(b.add(_))
        assertTrue(b.isEmpty)
    }

    def testEndWith: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 4).endWith(b.add(99))foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4,99), vector.from(b))
    }

    def testEndWith2: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 4).endWith(b.add(99)).endWith(b.add(98)).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4,99,98), vector.from(b))
    }
}
