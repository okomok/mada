

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.vector._
import junit.framework.Assert._
import detail.Example._


class CycleTest extends junit.framework.TestCase {
    def testTrivial {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = fromArray(Array(4,23,0,12)).times(4)
        detail.TestVectorReadOnly(expected, actual)
    }

    def testEmpty {
        val actual = fromArray(empty1).times(40)
        detail.TestEmpty(actual)
    }

    def testEmpty2 {
        val actual = fromArray(example1).times(0)
        detail.TestEmpty(actual)
    }

    def testFusion {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = fromArray(Array(4,23,0,12)).times(2).times(2)
        detail.TestVectorReadOnly(expected, actual)
    }
}
