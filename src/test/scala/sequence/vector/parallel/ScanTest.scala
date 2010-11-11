

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.{Vector, vector}
import mada.sequence.vector._

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Sample._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class ScanTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val v: Vector[Int] = Array(1,2,3,4,5,6,7,8,9,10,11)
        assertEquals(v.scan(4)(_ + _), v.parallel.scan(4)(_ + _))
    }

    def testBound: Unit = {
        assertEquals(vector.single(3), empty.of[Int].parallel.scan(3)(_ + _))
    }
}
