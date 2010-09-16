

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class MergeTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val r1 = reactive.Of(1,2,3)
        val r2 = reactive.Of(4,5)
        val out = new java.util.ArrayList[Int]
        for (x <- r1 merge r2) {
            out.add(x)
        }
        assertEquals(iterative.Of(1,2,3,4,5), iterative.from(out))
    }

    def testNonTrivial: Unit = {
        val r1 = reactive.Of(1,2,3)
        val r2 = reactive.Of(4,5)
        val r3 = reactive.Of(6,7,8,9)
        val out = new java.util.ArrayList[Int]
        for (x <- r1 merge r2 merge r3) {
            out.add(x)
        }
        assertEquals(iterative.Of(1,2,3,4,5,6,7,8,9), iterative.from(out))
    }

}
