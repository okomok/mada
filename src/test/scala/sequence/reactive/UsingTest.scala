

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

        class TrivialResource extends reactive.NoEndResource[Int] {
            override def closeResource = autoEnd = true
            override protected def openResource(f: Int => Unit) {
                job = _ => {f(10); f(12); f(2); f(8)}
            }
            var job: Unit => Unit = null
        }

        val r = new TrivialResource
        r.used.foreach { x =>
            if (x == 2) {
                throw new AssertionError
            } else {
                out.add(x)
            }
        }

        try {
            r.job()
        } catch {
            case x: AssertionError => thrown = true
            case _ => fail("doh")
        }

        assertTrue(thrown)
        assertTrue(autoEnd)
        assertEquals(iterative.Of(10, 12), iterative.from(out))
    }
}
