

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AddableTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val rx = reactive.Addable(12,13)
        val out = new java.util.ArrayList[Int]
        rx.foreach(out.add(_))
        rx add 5
        rx add 4
        rx add 6
        assertEquals(iterative.Of(12,13,5,4,6), iterative.from(out))
    }

    def testTrivial2 {
        val rx = reactive.Addable[Int]()
        val out = new java.util.ArrayList[Int]
        rx.foreach(out.add(_))
        rx add 5
        rx add 4
        rx add 6
        assertEquals(iterative.Of(5,4,6), iterative.from(out))
    }

    def testParallel: Unit = {
    //    for (_ <- 0 to 30) {
            val src = new IntSenders(vector.Of(1,2,3,4,5,6,7,8,9,10), vector.Of(7,7,7,7,7,7,7,7,7,7))
            val dst = new IntReceiver(vector.Of(1,2,3,4,5,6,7,7,7,7,7,7,7,7,7,7,7,8,9,10,10))
            val rx = reactive.Addable[Int](10)
            rx.foreach(dst)
            src(0).foreach(rx)
            src(1).foreach(rx)
            src.activate
            src.shutdown(dst.assertMe)
    //    }
    }

    def testSignal {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Addable(1)
        val b = reactive.Addable(2)
        for (x <- a; y <- b) {
            out.add(x + y)
        }
        a add 7
        b add 35
        assertEquals(vector.Of(3,9,36,42), vector.from(out))

    }

    def testSignal2 {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Addable(1)
        val b = reactive.Addable(2)

        a.zip(b).
            collect{ case (x: Int, y: Int) => x + y }.
            foreach{ sum => out.add(sum) }
        a add 7
        b add 35
        assertEquals(vector.Of(3,42), vector.from(out))
    }

    def testSignal3 {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Addable(1,2,3)
        val b = reactive.Addable(4,5)
        for (x <- a; y <- b) {
            out.add(x + y)
        }
        assertEquals(vector.Of(5,6,6,7,7,8), vector.from(out))
        a add 7
        assertEquals(vector.Of(5,6,6,7,7,8,11,12), vector.from(out))
        b add 35
        assertEquals(vector.Of(5,6,6,7,7,8,11,12,36,37,38,42), vector.from(out))

    }
}
