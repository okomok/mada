

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector._
import junit.framework.Assert._
import madatest.vec.detail.Example._


class SortTest {
    def testTrivial {
        val actual = arrayVector(example1).cut.sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testImplicit {
        val actual = Vector.sort(arrayVector(example1).cut)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArray {
        val actual = arrayVector(example1).sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayWindow {
        val actual = arrayVector(example1).window(0, 0).window(0, example1.length).sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }

    def testOptimizeArrayList {
        val actual = jclListVector(arrayVector(example1).toJclArrayList).sort(_ < _)
        detail.TestVectorReadOnly(example1Sorted, actual)
    }
}
