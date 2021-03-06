

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ResourceTest extends org.scalatest.junit.JUnit3Suite {

    class TrivialResource extends reactive.NoEndResource[Int] {
        override protected def closeResource = { job = null }
        override protected def openResource(f: Int => Unit) {
            assertEquals(null, job)
            job = { _ => f(10); f(12); f(2); f(8) }
        }
        var job: Unit => Unit = null
    }

    def testTrivial {
        val r = new TrivialResource
        def doIt() {
            val out = new java.util.ArrayList[Int]
            r.foreach { x =>
                out.add(x)
            }
            r.job()
            r.close
            assertEquals(iterative.Of(10, 12, 2, 8), iterative.from(out))
        }

        doIt()
    }

    def testThrown {
        val r = new TrivialResource
        def doIt() {
            val out = new java.util.ArrayList[Int]
            r.foreach { x =>
                out.add(x)
            }
            r.job()
            //r.close()
            assertEquals(iterative.Of(10, 12, 2, 8), iterative.from(out))
        }

        doIt()
        var thrown = false
        try {
            doIt()
        } catch {
            case reactive.ReactiveOnceException(_) => thrown = true
            case _ => fail("doh")
        }
        assertTrue(thrown)
    }

}
