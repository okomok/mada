

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.vector._

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class FoldTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val v = mada.sequence.vector.from(example1)
        assertEquals(v.foldLeft(3)(_ + _), v.parallel.fold(3)(_ + _))
        assertEquals(v.foldLeft(3)(_ + _), v.parallelBy(6).fold(3)(_ + _))
        assertEquals(v.foldLeft(3)(_ + _), v.parallelBy(1000).fold(3)(_ + _))
    }
}
