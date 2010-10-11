

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class VarTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val rx = new reactive.Var(12)
        val out = new java.util.ArrayList[Int]
        rx.foreach(out.add(_))
        rx := 5
        rx := 4
        rx := 6
        assertEquals(iterative.Of(12,5,4,6), iterative.from(out))
    }

    def testTrivial2 {
        val rx = new reactive.Var[Int]
        val out = new java.util.ArrayList[Int]
        rx.foreach(out.add(_))
        rx := 5
        rx := 4
        rx := 6
        assertEquals(iterative.Of(5,4,6), iterative.from(out))
    }
/* rejected
    def testTrivial3 {
        val rx = new reactive.Var[Int]
        val out = new java.util.ArrayList[Int]
        rx := 12
        rx.foreach(out.add(_))
        rx := 5
        rx := 4
        rx := 6
        assertEquals(iterative.Of(12, 5,4,6), iterative.from(out))
    }

    def testTrivial4 {
        val rx = new reactive.Var(10)
        val out = new java.util.ArrayList[Int]
        rx := 12
        rx.foreach(out.add(_))
        rx := 5
        rx := 4
        rx := 6
        assertEquals(iterative.Of(12, 5,4,6), iterative.from(out))
    }
*/
    def testParallel: Unit = {
    //    for (_ <- 0 to 30) {
            val src = new IntSenders(vector.Of(1,2,3,4,5,6,7,8,9,10), vector.Of(7,7,7,7,7,7,7,7,7,7))
            val dst = new IntReceiver(vector.Of(1,2,3,4,5,6,7,7,7,7,7,7,7,7,7,7,7,8,9,10,10))
            val rx = new reactive.Var[Int](10)
            rx.foreach(dst)
            src(0).foreach(rx.:=)
            src(1).foreach(rx.:=)
            src.activate
            src.shutdown(dst.assertMe)
    //    }
    }

    def testSignal {
        val out = new java.util.ArrayList[Int]
        val a = new reactive.Var(1)
        val b = reactive.Var(2)
        for (x <- a; y <- b) {
            out.add(x + y)
        }
        assertEquals(vector.Of(3), vector.from(out))
        a := 7
        b := 35
        b := 36
        a := 8
        assertEquals(vector.Of(3,9,42,43,44), vector.from(out))
    }

    def testSignal2 {
        val out = new java.util.ArrayList[Int]
        val a = new reactive.Var(1)
        val b = new reactive.Var(2)

        a.zip(b).
            collect{ case (x: Int, y: Int) => x + y }.
            foreach{ sum => out.add(sum) }
        a := 7
        b := 35
        assertEquals(vector.Of(3,42), vector.from(out))
    }

    def testSignal3 {
        val out = new java.util.ArrayList[Int]
        val a = new reactive.Var[Int](1)
        val b = new reactive.Var[Int]//(2)
        val c = new reactive.Var[Int](3)
        for (x <- a; y <- b; z <- c) {
            out.add(x + y + z)
        }
        assertEquals(vector.empty.of[Int], vector.from(out)); out.clear

        b := 2
        assertEquals(vector.Of(1+2+3), vector.from(out)); out.clear

        a := 2
        assertEquals(vector.Of(2+2+3), vector.from(out)); out.clear

        b := 1
        assertEquals(vector.Of(2+1+3), vector.from(out)); out.clear

        c := 4
        assertEquals(vector.Of(2+1+4), vector.from(out)); out.clear

        b := 5
        assertEquals(vector.Of(2+5+4), vector.from(out)); out.clear

        c := 7
        assertEquals(vector.Of(2+5+7), vector.from(out)); out.clear

        a := 8
        assertEquals(vector.Of(8+5+7), vector.from(out)); out.clear

        a := 9
        assertEquals(vector.Of(9+5+7), vector.from(out)); out.clear

        c := 3
        assertEquals(vector.Of(9+5+3), vector.from(out)); out.clear
    }

    def testTwice {
        val out = new java.util.ArrayList[Int]
        val a = new reactive.Var(1)
        val b = reactive.Var(2)
        for (x <- a; y <- b) {
            out.add(x + y)
        }
        assertEquals(vector.Of(3), vector.from(out))
        a := 7
        b := 35
        b := 36
        a := 8
        assertEquals(vector.Of(3,9,42,43,44), vector.from(out))

        // reset foreach.
        out.clear
        for (x <- a; y <- b) {
            out.add(x - y)
        }
        assertEquals(vector.Of(8 - 36), vector.from(out)); out.clear
        a := 3
        assertEquals(vector.Of(3 - 36), vector.from(out)); out.clear
        b := 5
        assertEquals(vector.Of(3 - 5), vector.from(out)); out.clear
    }
}
