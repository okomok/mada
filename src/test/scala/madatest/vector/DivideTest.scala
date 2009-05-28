

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}

import mada.vector.fromArray
import junit.framework.Assert._
import madatest.vectortest.detail.Example._


class DivideTest {
    def testTrivial: Unit = {
        // 0  1  2  3  4  5    6  7  8  9 10 11   12 13 14
        // 0,18,14,17,19, 8,  13, 6, 4,23, 0,12,  15,11, 4
        val actual = mada.vector.from(example1).divide(6)
        assertEquals(3, actual.size)
        assertEquals(actual(0), mada.vector.from(Array(0,18,14,17,19,8)))
        assertEquals(actual(1), mada.vector.from(Array(13, 6, 4,23, 0,12)))
        assertEquals(actual(2), mada.vector.from(Array(15,11, 4)))
    }

    def testBig: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = mada.vector.from(example1).divide(600)
        assertEquals(1, actual.size)
        assertEquals(actual(0), mada.vector.from(example1))
    }

    def testRegion: Unit = {
        // 0  1  2   3    4  5  6  7    8  9 10
        // 14,17,19, 8,  13, 6, 4,23,   0,12,15
        val actual = mada.vector.from(example1).copy.region(2, 13).divide(4)
        assertEquals(3, actual.size)
        detail.TestVectorReadWrite(Array(14,17,19, 8), actual.nth(0))
        detail.TestVectorReadWrite(Array(13, 6, 4,23), actual.nth(1))
        detail.TestVectorReadWrite(Array( 0,12,15), actual.nth(2))
    }


    def testBound: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = mada.vector.from(example1).divide(15)
        assertEquals(1, actual.size)
        assertEquals(actual(0), mada.vector.from(example1))
    }

    def testUndivide: Unit = {
        val actual = vector.undivide(mada.vector.from(example1).copy.divide(6).seal)
        assertEquals(15, actual.size)
        detail.TestVectorReadWrite(example1, actual)
    }
    def testUndivideBound: Unit = {
        val actual = vector.undivide(mada.vector.from(example1).divide(1).seal)
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideBoundBig: Unit = {
        val actual = vector.undivide(mada.vector.from(example1).divide(1000).seal)
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideEmpty: Unit = {
        val actual = vector.undivide(mada.vector.from(empty1).divide(10).seal)
        detail.TestEmpty(actual)
    }

    def testUndivideFusion: Unit = {
        val actual = vector.undivide(mada.vector.from(example1).divide(1000))
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideRegion: Unit = {
        val dv =mada.vector.from(example1).copy.divide(6).seal.region(1, 2)
        val actual = vector.undivide(dv)
        assertEquals(6, actual.size)
        detail.TestVectorReadWrite(Array(13, 6, 4,23, 0,12), actual)
    }
}
