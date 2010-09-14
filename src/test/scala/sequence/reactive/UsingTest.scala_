

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class UsingTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8,9)

        val out = new java.util.ArrayList[Int]

        var autoBegin = false
        var autoEnd = false
        var thrown = false

        try {
            val u =
                t filter {
                    _ > 3
                } using {
                    new mada.auto.Resource[Unit] {
                        override def open = { autoBegin = true; () }
                        override def close = autoEnd = true
                    }
                }

            for (x <- u) {
                x.map { e =>
                    if (e == 8) {
                        throw new AssertionError
                    } else {
                        e + 10
                    }
                } foreach { e =>
                    out.add(e)
                }
            }
        } catch {
            case x: AssertionError => thrown = true
            case _ => fail("doh")
        }

        assertTrue(thrown)
        assertTrue(autoBegin)
        assertTrue(autoEnd)
        assertEquals(iterative.Of(14,15,16,17), iterative.from(out))
    }
}
