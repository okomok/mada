

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ReplaceTest extends org.scalatest.junit.JUnit3Suite {

    def testLonger: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).replace(Iterative.Of(9,8,7,6,5,4,3))) {
            s.add(x)
        }
        assertEquals(vector.Of(9,8,7,6,5), vector.from(s))
    }

    def testShorter: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).replace(Iterative.Of(9,8))) {
            s.add(x)
        }
        assertEquals(vector.Of(9,8,2,3,4), vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).replace(Iterative.empty.of[Int])) {
            s.add(x)
        }
        assertEquals(vector.Of(0,1,2,3,4), vector.from(s))
    }

    def testInfinite: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).generate(Iterative.repeat(9))) {
            s.add(x)
        }
        assertEquals(vector.Of(9,9,9,9,9), vector.from(s))
    }

    def testRegion1: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).replaceRegion(2,4,Iterative.Of(9,8,7,6,5,4,3))) {
            s.add(x)
        }
        assertEquals(vector.Of(0,1,9,8,4), vector.from(s))
    }

}
