

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.vector._
import junit.framework.Assert._
import detail.Example._


class CycleTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = from(Array(4,23,0,12)).times(4)
        detail.TeztVectorReadOnly(expected, actual)
    }

    def testEmpty {
        val actual = from(empty1).times(40)
        detail.TeztEmpty(actual)
    }

    def testEmpty2 {
        val actual = from(example1).times(0)
        detail.TeztEmpty(actual)
    }

    def testFusion {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = from(Array(4,23,0,12)).times(2).times(2)
        detail.TeztVectorReadOnly(expected, actual)
    }
}
