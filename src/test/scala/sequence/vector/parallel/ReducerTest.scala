

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


class Scan1Test extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val v: Vector[Int] = Array(1,2,3,4,5,6,7,8,9,10,11)
        assertEquals(v.scan1(_ + _), v.parallel.scan1(_ + _))
    }

    def testBound: Unit = {
        val v = vector.single(11)
        assertEquals(vector.single(11), v.parallel.scan1(_ + _))
        assertEquals(v.scan1(_ + _), v.parallel.scan1(_ + _))
    }

    def testBound2: Unit = {
        val v: Vector[Int] = Array(1,2,3,4)
        assertEquals(v.scan1(_ + _), v.parallelBy(2).scan1(_ + _))
    }
}
