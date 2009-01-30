

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
        // 0  1  2  3  4  5    6  7  8  9 10 11   12 13 14
        // 0,18,14,17,19, 8,  13, 6, 4,23, 0,12,  15,11, 4
        val actual = madaVector(example1).divide(6)
        assertEquals(3, actual.size)
        assertEquals(actual(0), madaVector(Array(0,18,14,17,19,8)))
        assertEquals(actual(1), madaVector(Array(13, 6, 4,23, 0,12)))
        assertEquals(actual(2), madaVector(Array(15,11, 4)))
    }

    def testBig: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = madaVector(example1).divide(600)
        assertEquals(1, actual.size)
        assertEquals(actual(0), madaVector(example1))
    }

    def testRegion: Unit = {
        // 0  1  2   3    4  5  6  7    8  9 10
        // 14,17,19, 8,  13, 6, 4,23,   0,12,15
        val actual = madaVector(example1).clone.region(2, 13).divide(4)
        assertEquals(3, actual.size)
        detail.TestVectorReadWrite(Array(14,17,19, 8), actual.nth(0))
        detail.TestVectorReadWrite(Array(13, 6, 4,23), actual.nth(1))
        detail.TestVectorReadWrite(Array( 0,12,15), actual.nth(2))
    }


    def testBound: Unit = {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val actual = madaVector(example1).divide(15)
        assertEquals(1, actual.size)
        assertEquals(actual(0), madaVector(example1))
    }

    def testUndivide: Unit = {
        val actual = Vector.undivide(madaVector(example1).clone.divide(6).cut)
        assertEquals(15, actual.size)
        detail.TestVectorReadWrite(example1, actual)
    }
    def testUndivideBound: Unit = {
        val actual = Vector.undivide(madaVector(example1).divide(1).cut)
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideBoundBig: Unit = {
        val actual = Vector.undivide(madaVector(example1).divide(1000).cut)
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideEmpty: Unit = {
        val actual = Vector.undivide(madaVector(empty1).divide(10).cut)
        detail.TestEmpty(actual)
    }

    def testUndivideFusion: Unit = {
        val actual = Vector.undivide(madaVector(example1).divide(1000))
        assertEquals(15, actual.size)
        detail.TestVectorReadOnly(example1, actual)
    }

    def testUndivideRegion: Unit = {
        val dv =madaVector(example1).clone.divide(6).cut.region(1, 2)
        val actual = Vector.undivide(dv)
        assertEquals(6, actual.size)
        detail.TestVectorReadWrite(Array(13, 6, 4,23, 0,12), actual)
    }
}
