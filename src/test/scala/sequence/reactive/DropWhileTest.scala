

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class DropWhileTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial0: Unit = {
        val a = reactive.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        for (x <- a.dropWhile(_ <= 4)) {
            b.add(x)
        }
        assertEquals(vector.Of(5,6), vector.from(b))
    }

    def testAll: Unit = {
        val a = reactive.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        for (x <- a.dropWhile(_ <= 10)) {
            b.add(x)
        }
        assertTrue(b.isEmpty)
    }

    def testEmpty: Unit = {
        val a = reactive.empty.of[Int]
        val b = new java.util.ArrayList[Int]
        for (x <- a.dropWhile(_ <= 10)) {
            b.add(x)
        }
        assertTrue(b.isEmpty)
    }

    def testNone: Unit = {
        val a = reactive.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        for (x <- a.dropWhile(_ > 10)) {
            b.add(x)
        }
        assertEquals(vector.Of(1,2,3,4,5,6), vector.from(b))
    }
}
