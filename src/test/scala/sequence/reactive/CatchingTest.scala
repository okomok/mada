

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class CatchingTest extends org.scalatest.junit.JUnit3Suite {

    def testCatch: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8,9)

        val out = new java.util.ArrayList[Int]

        t.filter {
            _ > 3
        } catching {
            case x: AssertionError => out.add(88)
        } map { e =>
            if (e == 8) {
                throw new AssertionError
            } else {
                e + 10
            }
        } foreach { e =>
            out.add(e)
        }

        assertEquals(iterative.Of(14,15,16,17,88,19), iterative.from(out))
    }

/*
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8,9)

        var finalOk = false

        val out = new java.util.ArrayList[Int]

        reactive.`try` {
            t.
            filter { _ > 3 }
        } `catch` {
            case x: AssertionError => out.add(88)
        } `finally` {
            finalOk = true
        } map { e =>
            if (e == 8) {
                throw new AssertionError
            } else {
                e + 10
            }
        } foreach { e =>
            out.add(e)
        }

        assertTrue(finalOk)
        assertEquals(iterative.Of(14,15,16,17,88,19), iterative.from(out))
    }
*/
}
