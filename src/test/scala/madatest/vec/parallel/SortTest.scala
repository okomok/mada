

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vectors
import mada.Vectors._
import junit.framework.Assert._
import madatest.vec.detail.Example._


class SortTest {
    def testTrivial {
        val actual = fromArray(example1).cut.parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testLong: Unit = {
        val actual = longSample1.clone.parallel.sortWith(_ < _)
        assertEquals(longSample1.clone.sortWith(_ < _), actual)
    }

    def testImplicit: Unit = {
        val actual = fromArray(example1).cut.parallel.sort
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = fromArray(example1).parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = fromArray(example1).window(0, 0).window(0, example1.length).parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = fromJclList(fromArray(example1).toJclArrayList).parallel.sortWith(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}

class SortParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.clone.parallel.cut.sortWith(_ < _)
    }
}

class SortNonParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.clone.cut.cut.sortWith(_ < _)
    }
}

class SortParallelPartitionTest extends NoBenchmark {
    override def run = {
        // mada.vec.parallel.SortWith.partition(longSample1.clone, (_: Int) < (_: Int), mada.vec.parallel.DefaultGrainSize(longSample1))
    }
}
