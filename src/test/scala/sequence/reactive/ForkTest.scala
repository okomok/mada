

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ForkTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Int]
        r.
            fork{r => r.reactTotal(e => out.add(e *  2))}.
            fork{r => r}.
            fork{r => r.reactTotal(e => out.add(e + 10))}.
            fork{r => r}.
            start

        assertEquals(iterative.Of(2,11,4,12,6,13,8,14,10,15,12,16), iterative.from(out))
    }

    def testDoing: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Int]
        r.
            reactTotal(e => out.add(e *  2)).
            reactTotal(e => out.add(e + 10)).
            start

        assertEquals(iterative.Of(2,11,4,12,6,13,8,14,10,15,12,16), iterative.from(out))
    }
}
