

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

        var autoEnd = false
        var thrown = false

        try {
            t filter {
                _ > 3
            } using {
                new java.io.Closeable {
                    override def close = autoEnd = true
                }
            } map { e =>
                if (e == 8) {
                    throw new AssertionError
                } else {
                    e + 10
                }
            } foreach { e =>
                out.add(e)
            }
        } catch {
            case x: AssertionError => thrown = true
            case _ => fail("doh")
        }

        assertTrue(thrown)
        assertTrue(autoEnd)
        assertEquals(iterative.Of(14,15,16,17), iterative.from(out))
    }

    def testUsed {
        val out = new java.util.ArrayList[Int]

        var autoEnd = false
        var thrown = false

        class TrivialResource extends reactive.Closeable[Int] {
            override def close = autoEnd = true
            override def foreach(f: Int => Unit) {
                f(10)
                f(12)
                f(2)
                f(8)
            }
        }

        val r = new TrivialResource
        try {
            for (x <- r.used) {
                if (x == 2) {
                    throw new AssertionError
                } else {
                    out.add(x)
                }
            }
        } catch {
            case x: AssertionError => thrown = true
            case _ => fail("doh")
        }

        assertTrue(thrown)
        assertTrue(autoEnd)
        assertEquals(iterative.Of(10, 12), iterative.from(out))

    }
}
