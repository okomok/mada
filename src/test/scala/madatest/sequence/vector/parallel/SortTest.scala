

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest; package paralleltest


// import mada.Compare.madaCompareFromGetOrdered


import mada.control
import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._


class SortTest {
    def testTrivial {
        val actual = fromArray(example1).seal.parallel.sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testLong: Unit = {
        val actual = longSample1.copy.parallel.sortBy(_ < _)
        assertEquals(longSample1.copy.sortBy(_ < _), actual)
    }

    def testImplicit: Unit = {
        val actual = fromArray(example1).seal.parallel.sort
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).parallel.sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).parallel.sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJList(fromArray(example1).toJList).parallel.sortBy(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}

class SortParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.copy.parallel.sortBy{ (x, y) => control.times(longCalc, 5); x < y }
    }
    override val grainCount = 1
}

class SortNonParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.copy.sortBy{ (x, y) => control.times(longCalc, 5); x < y }
    }
    override val grainCount = 1
}

class SortParallelPartitionTest extends NoBenchmark {
    override def run = {
        // mada.sequence.vector.parallel.SortBy.partition(longSample1.copy, (_: Int) < (_: Int), mada.sequence.vector.parallel.DefaultGrainSize(longSample1))
    }
}
