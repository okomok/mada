

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._
import mada.sequence.reactive.Exit


class OnceTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial {
        val out = new java.util.ArrayList[Int]
        var thrown = false

        class Trivial extends reactive.ReactiveOnce[Int] {
            override protected def forloopOnce(f: Int => Unit, k: Exit => Unit) {
                f(10)
                f(5)
            }
        }

        val r = new Trivial
        r.start
        try {
            r.start
        } catch {
            case reactive.ReactiveOnceException(_) => thrown = true
            case _ => fail("doh")
        }

        assertTrue(thrown)
    }
}
