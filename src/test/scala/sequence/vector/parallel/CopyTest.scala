

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.vector._

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class CopyTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val actual = mada.sequence.vector.from(sample1).parallel.copy
        detail.TeztVectorReadWrite(sample1, actual)
    }

    def testCopyTo: Unit = {
        val v = Of(1,2,3,4,5,6)
        val w = Of(9,8,7,6,5,4,3,2,1)
        assertEquals(Of(1,2,3,4,5,6), v.parallel.copyTo(w))
    }
}
