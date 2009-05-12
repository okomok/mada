

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.{Vector, vector}
import mada.vector._
import junit.framework.Assert._
import madatest.vectortest.detail.Example._


class SortTest {
    def testTrivial {
        val actual = fromArray(example1).seal.sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testImplicit {
        val actual = fromArray(example1).seal.sort
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJclList(fromArray(example1).toJclArrayList).sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}
