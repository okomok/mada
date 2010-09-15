

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


/*
class OnStartTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(1,2,3,4).onStart(s.add(99)).onStart(s.add(98))) {
            s.add(x)
        }
        assertEquals(vector.Of(99, 98, 1,2,3,4), vector.from(s))
    }
}


class OnEndTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(1,2,3,4).onEnd(s.add(99))) {
            s.add(x)
        }
        assertEquals(vector.Of(1,2,3,4, 99), vector.from(s))
    }
}
*/
