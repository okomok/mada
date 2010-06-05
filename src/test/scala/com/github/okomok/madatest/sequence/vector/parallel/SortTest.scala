

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.control
import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._


class SortTest extends junit.framework.TestCase {
    def testTrivial {
        val actual = fromArray(example1).seal.parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testLong: Unit = {
        val actual = longSample1.copy.parallel.sort
        assertEquals(longSample1.copy.sort, actual)
    }

    def testImplicit: Unit = {
        val actual = fromArray(example1).seal.parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJList(fromArray(example1).toJList).parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }
}

class SortParallelPerfTezt extends Benchmark {
    override def run = {
        longSample1.copy.parallel.sort(Ordering.fromLessThan[Int]{ (x, y) => control.times(longCalc, 5); x < y })
    }
    override val grainCount = 1
}

class SortNonParallelPerfTezt extends Benchmark {
    override def run = {
        longSample1.copy.sort(Ordering.fromLessThan[Int]{ (x, y) => control.times(longCalc, 5); x < y })
    }
    override val grainCount = 1
}

class SortParallelPartitionTezt extends Benchmark {
    override def run = {
        // mada.sequence.vector.parallel.Sort.partition(longSample1.copy, (_: Int) < (_: Int), mada.sequence.vector.parallel.DefaultGrainSize(longSample1))
    }
}
