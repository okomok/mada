

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ZipTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3)
        val u = reactive.Of("2","3","4")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertEquals(iterative.Of((1,"2"),(2,"3"),(3,"4")), iterative.from(out))
    }

    def testEmpty1: Unit = {
        val t = reactive.empty.of[Int]
        val u = reactive.Of("2","3","4")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertTrue(out.isEmpty)
    }

    def testEmpty2: Unit = {
        val t = reactive.empty.of[Int]
        val u = reactive.empty.of[String]
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertTrue(out.isEmpty)
    }

    def testOneShorter1: Unit = {
        val t = reactive.Of(1,2)
        val u = reactive.Of("2","3","4")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertEquals(iterative.Of((1,"2"),(2,"3")), iterative.from(out))
    }

    def testOneShorter2: Unit = {
        val t = reactive.Of(1,2,3)
        val u = reactive.Of("2","3")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertEquals(iterative.Of((1,"2"),(2,"3")), iterative.from(out))
    }

    def testMuchShorter1: Unit = {
        val t = reactive.Of(1,2)
        val u = reactive.Of("2","3","4","5","6")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertEquals(iterative.Of((1,"2"),(2,"3")), iterative.from(out))
    }

    def testMuchShorter2: Unit = {
        val t = reactive.Of(1,2,3,4,5,6)
        val u = reactive.Of("2","3")
        val out = new java.util.ArrayList[(Int, String)]
        t.zip(u).foreach(out.add(_))
        assertEquals(iterative.Of((1,"2"),(2,"3")), iterative.from(out))
    }

    def testParallel: Unit = {
        for (_ <- 0 to 30) {
            val src = new IntSenders(vector.Of(1,2,3,4,5,6), vector.Of(7,7,7,7))
            val dst = new IntReceiver(vector.Of(8,9,10,11))
            (src(0) zip src(1)).map{case (x, y) => x + y}.foreach(dst)
            src.activate
            src.shutdown(dst.assertMe)
        }
    }

}
