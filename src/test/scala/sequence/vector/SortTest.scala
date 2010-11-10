

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._


class SortTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial {
        val actual = from(sample1).seal.sort(Ordering.fromLessThan[Int](_ < _))
        detail.TeztVectorReadOnly(sample1Sorted, actual)
    }

    def testImplicit {
        val actual = from(sample1).seal.sort
        detail.TeztVectorReadOnly(sample1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = from(sample1).sort(Ordering.fromLessThan[Int](_ < _))
        detail.TeztVectorReadOnly(sample1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = from(sample1).window(0, 0).window(0, sample1.length).sort(Ordering.fromLessThan[Int](_ < _))
        detail.TeztVectorReadOnly(sample1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = from(from(sample1).toJList).sort(Ordering.fromLessThan[Int](_ < _))
        detail.TeztVectorReadOnly(sample1Sorted, actual)
    }
}
