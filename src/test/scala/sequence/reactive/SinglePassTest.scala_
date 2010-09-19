

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class SinglePassTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val r = reactive.Of(1,2,3,4).singlePass
        var thrown = false

        assertEquals(iterative.Of(1,2,3,4), r.toIterative)

        try {
            r.start
        } catch {
            case reactive.SinglePassException(_) => thrown = true
        }

        assertTrue(thrown)
    }
}
