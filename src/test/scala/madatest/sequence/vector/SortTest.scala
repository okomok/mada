

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest


// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._


import mada.function.toOrdering


class SortTest {
    def testTrivial {
        val actual = fromArray(example1).seal.sort(toOrdering[Int](_ < _))
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testImplicit {
        val actual = fromArray(example1).seal.sort
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).sort(toOrdering[Int](_ < _))
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).sort(toOrdering[Int](_ < _))
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJList(fromArray(example1).toJList).sort(toOrdering[Int](_ < _))
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}
