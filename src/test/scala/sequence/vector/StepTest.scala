

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import mada.sequence.vector.from
import junit.framework.Assert._
import detail.Sample._


class StepTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val expected = Array(0,17,13,23,15)
        val actual = from(sample1).step(3)
        detail.TeztVectorReadWrite(expected, actual)
    }

    def testTrivial2 {
        val expected = Array(17,13,23,15)
        val actual = from(sample1).drop(3).step(3)
        detail.TeztVectorReadWrite(expected, actual)
    }

    def testTrivial3 {
        //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 14,17,19, 8,13, 6, 4,23, 0
        val expected = Array(14, 8, 4)
//        println("subvector:" + vector.Region(from(sample1), 2, 11).toString)
        val actual = vector.Region(from(sample1), 2, 11).step(3)
//        println(actual)
        detail.TeztVectorReadWrite(expected, actual)
    }

    def testBounds {
        val expected = Array(0, 6, 4)
        val actual = from(sample1).step(7) // 15 / 7 = 2..1
        detail.TeztVectorReadWrite(expected, actual)
    }

    def testOne {
        val actual = from(sample1).step(1)
        detail.TeztVectorReadWrite(sample1, actual)
    }

    def testBigStride {
        val v = from(sample1).drop(2).step(99)
        assertEquals(14, v.head)
        assertEquals(1, v.size)
    }

    def testEmpty {
        detail.TeztEmpty(from(empty1).step(10))
    }

    def testStepStep: Unit = {
        val expected = Array(17,23)
        val actual = from(sample1).step(3).seal.drop(1).step(2)
        detail.TeztVectorReadWrite(expected, actual)
    }

    def testStepDrop: Unit = {
        val expected = Array(17,23)
        val actual = from(sample1).step(3).drop(1).step(2)
        detail.TeztVectorReadWrite(expected, actual)
    }

    def testFusion: Unit = {
        val expected = Array(0,13,15)
        val actual = from(sample1).step(3).step(2)
        detail.TeztVectorReadWrite(expected, actual)
    }
}
