

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vector
import mada.Vector._
import junit.framework.Assert._
import madatest.vec.detail.Example._


class SortTest {
    def testTrivial {
        val actual = arrayVector(example1).cut.parallel.sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testLong: Unit = {
        val actual = longSample1.clone.parallel.sort(_ < _)
        assertEquals(longSample1.clone.sort(_ < _), actual)
    }
/*
    def testImplicit {
        val actual = Vector.parallel.sort(arrayVector(example1).cut)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
*/
    def testOptimizeArray {
        val actual = arrayVector(example1).parallel.sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = arrayVector(example1).window(0, 0).window(0, example1.length).parallel.sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = jclListVector(arrayVector(example1).toJclArrayList).parallel.sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}

class SortParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.clone.parallel.cut.sort(_ < _)
    }
}

class SortNonParallelPerfTest extends NoBenchmark {
    override def run = {
        longSample1.clone.cut.cut.sort(_ < _)
    }
}

class SortParallelPartitionTest extends NoBenchmark {
    override def run = {
        mada.vec.parallel.Sort.partition(longSample1.clone, (_: Int) < (_: Int), mada.vec.parallel.DefaultGrainSize(longSample1))
    }
}
