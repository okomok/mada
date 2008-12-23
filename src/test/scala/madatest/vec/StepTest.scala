

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import mada.Vector.fromArray
import junit.framework.Assert._
import detail.Example._


class StepTest {
    def testTrivial {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val expected = Array(0,17,13,23,15)
        val actual = fromArray(example1).step(0, 3)
        detail.TestVectorReadWrite(expected, actual)
    }

    def testTrivial2 {
        val expected = Array(17,13,23,15)
        val actual = fromArray(example1).step(3, 3)
        detail.TestVectorReadWrite(expected, actual)
    }

    def testBounds {
        val expected = Array(0, 6, 4)
        val actual = fromArray(example1).step(0, 7) // 15 / 7 = 2..1
        detail.TestVectorReadWrite(expected, actual)
    }

    def testOne {
        val actual = fromArray(example1).step(0, 1)
        detail.TestVectorReadWrite(example1, actual)
    }

    def testBigStride {
        val v = fromArray(example1).step(2, 99)
        assertEquals(14, v(0))
        assertEquals(1L, v.size)
    }

    def testEmpty {
        detail.TestEmpty(fromArray(empty1).step(0, 10))
    }

    def testStepStep: Unit = {
        val expected = Array(17,23)
        val actual = fromArray(example1).step(0, 3).cut.step(1, 2)
        detail.TestVectorReadWrite(expected, actual)
    }

    def testFusion: Unit = {
        val expected = Array(17,23)
        val actual = fromArray(example1).step(0, 3).step(1, 2)
        detail.TestVectorReadWrite(expected, actual)
    }
}
