

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.util
import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._


class SortTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial {
        val actual = from(example1).seal.parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testLong: Unit = {
        val actual = longSample1.copy.parallel.sort
        assertEquals(longSample1.copy.sort, actual)
    }

    def testImplicit: Unit = {
        val actual = from(example1).seal.parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = from(example1).parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = from(example1).window(0, 0).window(0, example1.length).parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = from(from(example1).toJList).parallel.sort
        detail.TeztVectorReadOnly(example1Sorted, actual)
    }
}

class SortParallelPerfTest extends Benchmark /*with org.scalatest.Suite*/ {
    override def run = {
        for (_ <- 0 to 50)
            longSample1.copy.parallel.sort(Ordering.fromLessThan[Int]{ (x, y) => x < y })
    }
    override val grainCount = 1
}

class SortNonParallelPerfTest extends Benchmark /*with org.scalatest.Suite*/ {
    override def run = {
        for (_ <- 0 to 50)
            longSample1.copy.sort(Ordering.fromLessThan[Int]{ (x, y) => x < y })
    }
    override val grainCount = 1
}

class SortParallelPartitionTezt extends Benchmark {
    override def run = {
        // mada.sequence.vector.parallel.Sort.partition(longSample1.copy, (_: Int) < (_: Int), mada.sequence.vector.parallel.DefaultGrainSize(longSample1))
    }
}
