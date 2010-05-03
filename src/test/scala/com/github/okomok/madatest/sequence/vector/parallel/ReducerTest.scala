

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.{Vector, vector}
import mada.sequence.vector._

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class ReducerTest {
    def testTrivial: Unit = {
        val v: Vector[Int] = Array(1,2,3,4,5,6,7,8,9,10,11)
        assertEquals(v.reducer(_ + _), v.parallel.reducer(_ + _))
    }

    def testBound: Unit = {
        val v = vector.single(11)
        assertEquals(vector.single(11), v.parallel.reducer(_ + _))
        assertEquals(v.reducer(_ + _), v.parallel.reducer(_ + _))
    }

    def testBound2: Unit = {
        val v: Vector[Int] = Array(1,2,3,4)
        assertEquals(v.reducer(_ + _), v.parallelBy(2).reducer(_ + _))
    }
}
