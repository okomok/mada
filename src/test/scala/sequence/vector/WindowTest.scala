

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import junit.framework.Assert._


class WindowTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val ac = vector.range(0, 10).window(1, 3)
        assertEquals(vector.range(1, 3), ac)
    }

    def testNontrivial: Unit = {
        val ac = vector.range(0, 10).window(3, 9).seal.window(-2, 5)
        assertEquals(vector.range(1, 8), ac)
    }

    def testFusion: Unit = {
        val ac = vector.range(0, 10).window(3, 9).window(-2, 5)
        assertEquals(vector.range(1, 8), ac)
    }
}
