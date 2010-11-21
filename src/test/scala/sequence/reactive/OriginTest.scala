

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._
import mada.eval


class OriginTest extends org.scalatest.junit.JUnit3Suite {

    def testThreaded { // force to create a thread.
        val c = new java.util.concurrent.CountDownLatch(1)
        val a = new java.util.ArrayList[Int]
        for (x <- reactive.origin(mada.eval.Threaded).generate(0 until 10).onExit(_ =>c.countDown)) {
            a.add(x)
        }
        c.await
        expect(Vector.range(0, 10))(Vector.from(a))
    }

    def testAsync { // in the thread pool.
        val c = new java.util.concurrent.CountDownLatch(1)
        val a = new java.util.ArrayList[Int]
        for (x <- reactive.origin(mada.eval.Async).generate(0 until 10).onExit(_ =>c.countDown)) {
            a.add(x)
        }
        c.await
        expect(Vector.range(0, 10))(Vector.from(a))
    }

}


class OriginStrictTest  extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.origin(mada.eval.Strict).generate(Iterative.Of(9,8,7,6,5))) {
            s.add(x)
        }
        assertEquals(vector.Of(9,8,7,6,5), vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.origin(mada.eval.Strict).generate(Iterative.empty.of[Int])) {
            s.add(x)
        }
        assertTrue(s.isEmpty)
    }

    def testRandom: Unit = {
        val k = new java.util.Random
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.origin(mada.eval.Strict).generate(iterative.Randoms.OfInt(k)).take(3)) {
            s.add(x)
            //println(x)
        }
        assertEquals(3, s.size)
    }
}
