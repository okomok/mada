

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
        val a = vector.empty.of[Int]
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

    def testThen: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 4).onExit(_ =>b.add(99)).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4,99), vector.from(b))
    }

    def testThen2: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).takeWhile(_ <= 4).onExit(_ =>b.add(98)).onExit(_ =>b.add(99)).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4,98,99), vector.from(b))
    }

    def testThenAppend: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        (reactive.from(a).takeWhile(_ <= 4) ++ reactive.Of(5,6,7)).onExit(_ =>b.add(99)).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3,4,5,6,7,99), vector.from(b))
    }
}
