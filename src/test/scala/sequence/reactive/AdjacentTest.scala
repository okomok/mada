

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AdjacentTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3,4)
        val out = new java.util.ArrayList[(Int, Int)]
        t.adjacent.foreach(out.add(_))
        assertEquals(iterative.Of((1,2),(2,3),(3,4)), iterative.from(out))
    }

    def testEmpty: Unit = {
        val t = reactive.empty.of[Int]
        val out = new java.util.ArrayList[(Int, Int)]
        t.adjacent.foreach(out.add(_))
        assertTrue(out.isEmpty)
    }

    def testOne: Unit = {
        val t = reactive.Of(5)
        val out = new java.util.ArrayList[(Int, Int)]
        t.adjacent.foreach(out.add(_))
        assertTrue(out.isEmpty)
    }

}
