

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class RistTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val rx = reactive.Rist(12,13)
        val out = new java.util.ArrayList[Int]
        rx.foreach(out.add(_))
        rx add 5
        rx add 4
        rx add 6
        assertEquals(iterative.Of(12,13,5,4,6), iterative.from(out))
    }

    def testTrivial2 {
        val rx = reactive.Rist[Int]()
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
            val rx = reactive.Rist[Int](10)
            rx.foreach(dst)
            src(0).foreach(rx.add)
            src(1).foreach(rx.add)
            src.activate
            src.shutdown(dst.assertMe)
    //    }
    }

    def testSignal {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Rist(1)
        val b = reactive.Rist(2)
        for (x <- a; y <- b) {
            out.add(x + y)
        }
        a add 7
        b add 35
        assertEquals(vector.Of(3,9,36,42), vector.from(out))

    }

    def testSignal2 {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Rist(1)
        val b = reactive.Rist(2)

        a.zip(b).
            collect{ case (x: Int, y: Int) => x + y }.
            foreach{ sum => out.add(sum) }
        a add 7
        b add 35
        assertEquals(vector.Of(3,42), vector.from(out))
    }

    def testSignal3 {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Rist(1,2,3)
        val b = reactive.Rist(4,5)
        for (x <- a; y <- b) {
            out.add(x + y)
        }
        assertEquals(vector.Of(5,6,6,7,7,8), vector.from(out))
        a add 7
        assertEquals(vector.Of(5,6,6,7,7,8,11,12), vector.from(out))
        b add 35
        assertEquals(vector.Of(5,6,6,7,7,8,11,12,36,37,38,42), vector.from(out))
    }


    def testTwice {
        val out = new java.util.ArrayList[Int]
        val a = reactive.Rist(1,2,3)
        val b = reactive.Rist(4,5)
        for (x <- a; y <- b) {
            out.add(x + y) //  old listner
        }
        assertEquals(vector.Of(5,6,6,7,7,8), vector.from(out))
        a add 7
        assertEquals(vector.Of(5,6,6,7,7,8,11,12), vector.from(out))
        b add 35
        assertEquals(vector.Of(5,6,6,7,7,8,11,12,36,37,38,42), vector.from(out))

        // now, a: 1,2,3,7, b:4,5,35
        out.clear
        for (x <- a; y <- b) {
            out.add(x * y)
        }
        assertEquals(vector.Of(4,5,35, 8,10,70, 12,15,105, 28,35,35*7), vector.from(out))
        out.clear
        a add 10 // now, a:1,2,3,7,10
        assertEquals(vector.Of(14,15,35+10,  40,50,350), vector.from(out)) // old listner still listen.
        out.clear
        b add 30
        assertEquals(vector.Of(31,32,33,37,  30,30*2,30*3,30*7,  30+10, 30*10), vector.from(out))

    }
}
