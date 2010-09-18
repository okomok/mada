

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class IterativeTest extends org.scalatest.junit.JUnit3Suite {
    def testTo: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        assertEquals(iterative.Of(1,2,3,4,5,6), r.toIterative)
    }

    def testLong: Unit = {
        val r = reactive.from(vector.range(0,400))
        assertEquals(vector.range(0,400), r.toIterative)
    }
}
