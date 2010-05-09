

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}

import mada.sequence.vector.fromArray
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._


class DivideTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        // 0  1  2  3  4  5    6  7  8  9 10 11   12 13 14
        // 0,18,14,17,19, 8,  13, 6, 4,23, 0,12,  15,11, 4
        val actual = mada.sequence.vector.from(example1).divide(6)
        assertEquals(3, actual.size)
        assertEquals(actual(0), mada.sequence.vector.from(Array(0,18,14,17,19,8)))
        assertEquals(actual(1), mada.sequence.vector.from(Array(13, 6, 4,23, 0,12)))
        assertEquals(actual(2), mada.sequence.vector.from(Array(15,11, 4)))
    }

    def testBig: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = mada.sequence.vector.from(example1).divide(600)
        assertEquals(1, actual.size)
        assertEquals(actual(0), mada.sequence.vector.from(example1))
    }

    def testRegion: Unit = {
        // 0  1  2   3    4  5  6  7    8  9 10
        // 14,17,19, 8,  13, 6, 4,23,   0,12,15
        val actual = mada.sequence.vector.from(example1).copy.region(2, 13).divide(4)
        assertEquals(3, actual.size)
        detail.TestVectorReadWrite(Array(14,17,19, 8), actual.nth(0))
        detail.TestVectorReadWrite(Array(13, 6, 4,23), actual.nth(1))
        detail.TestVectorReadWrite(Array( 0,12,15), actual.nth(2))
    }


    def testBound: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = mada.sequence.vector.from(example1).divide(15)
        assertEquals(1, actual.size)
        assertEquals(actual(0), mada.sequence.vector.from(example1))
    }

    def testUndivide: Unit = {
        val actual = mada.sequence.vector.from(example1).copy.divide(6).seal.undivide
        assertEquals(15, actual.size)
        detail.TestVectorReadWrite(example1, actual)
    }
    def testUndivideBound: Unit = {
        val actual = mada.sequence.vector.from(example1).divide(1).seal.undivide
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideBoundBig: Unit = {
        val actual = mada.sequence.vector.from(example1).divide(1000).seal.undivide
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideEmpty: Unit = {
        val actual = mada.sequence.vector.from(empty1).divide(10).seal.undivide
        detail.TestEmpty(actual)
    }

    def testUndivideFusion: Unit = {
        val actual = mada.sequence.vector.from(example1).divide(1000).undivide
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideRegion: Unit = {
        val dv =mada.sequence.vector.from(example1).copy.divide(6).seal.region(1, 2)
        val actual = dv.undivide
        assertEquals(6, actual.size)
        detail.TestVectorReadWrite(Array(13, 6, 4,23, 0,12), actual)
    }
}
