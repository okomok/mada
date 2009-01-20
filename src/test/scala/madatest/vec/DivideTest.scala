

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector.Compatibles._
import mada.Vector.arrayVector
import junit.framework.Assert._
import madatest.vec.detail.Example._


class DivideTest {
    def testTrivial: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = madaVector(example1).divide(6)
        assertEquals(3L, actual.size)
        assertEquals(actual(0), madaVector(Array(0,18,14,17,19,8)))
        assertEquals(actual(1), madaVector(Array(13, 6, 4,23, 0,12)))
        assertEquals(actual(2), madaVector(Array(15,11, 4)))
    }

    def testBig: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = madaVector(example1).divide(600)
        assertEquals(1L, actual.size)
        assertEquals(actual(0), madaVector(example1))
    }

    def testBound: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = madaVector(example1).divide(15)
        assertEquals(1L, actual.size)
        assertEquals(actual(0), madaVector(example1))
    }
}
