

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package stltest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.vector._
import mada.sequence.{Vector, vector}
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class RandomShuffleTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val v = from(sample1)
        mada.sequence.vector.stl.randomShuffle(v, 0, v.size)
//        println(v.toString)
        mada.sequence.vector.stl.sort(v, 0, v.size)
        TeztVectorReadOnly(sample1Sorted, v)
    }

    def testMethod {
        val v = from(sample1)
        v.shuffle.sort
        TeztVectorReadOnly(sample1Sorted, v)
    }
}
