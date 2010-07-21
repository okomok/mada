

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.vector._


import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class EqualsTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        assertTrue(from(example1).parallelBy(1000) == mada.sequence.vector.from(example1))
        assertTrue(from(example1).parallelBy(6) == mada.sequence.vector.from(example1))
        assertTrue(from(example1).parallel == mada.sequence.vector.from(example1))
        assertFalse(from(example1).parallel == mada.sequence.vector.from(example2))
    }
}
