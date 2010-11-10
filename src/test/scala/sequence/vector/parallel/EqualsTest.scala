

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.vector._


import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class EqualsTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        assertTrue(from(sample1).parallelBy(1000) == mada.sequence.vector.from(sample1))
        assertTrue(from(sample1).parallelBy(6) == mada.sequence.vector.from(sample1))
        assertTrue(from(sample1).parallel == mada.sequence.vector.from(sample1))
        assertFalse(from(sample1).parallel == mada.sequence.vector.from(sample2))
    }
}
