

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

}
