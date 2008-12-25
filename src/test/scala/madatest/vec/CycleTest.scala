

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector._
import junit.framework.Assert._
import detail.Example._


class CycleTest {
    def testTrivial {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = arrayVector(Array(4,23,0,12)).cycle(4)
        detail.TestVectorReadOnly(expected, actual)
    }

    def testEmpty {
        val actual = arrayVector(empty1).cycle(40)
        detail.TestEmpty(actual)
    }

    def testEmpty2 {
        val actual = arrayVector(example1).cycle(0)
        detail.TestEmpty(actual)
    }

    def testFusion {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = arrayVector(Array(4,23,0,12)).cycle(2).cycle(2)
        detail.TestVectorReadOnly(expected, actual)
    }
}
