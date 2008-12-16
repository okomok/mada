

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng.detail


import mada.rng.From._
import mada.rng.Rng
import mada.rng._
import mada.rng.stl.RandomShuffle._
import junit.framework.Assert._


object TestRandomAccessReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(Traversal.RandomAccess, actual.traversal)
        impl(expected, actual)
    }

    def impl[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = {
        TestRandomAccessReadOnly.impl(expected, actual)

        // test offset
        CombSort(actual)
        val ex = CopyArray(expected); CombSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)

        // test offsetRead/Write
        actual./.stl_randomShuffle./
        mada.rng.stl.detail.IntroSort[A](actual, _ < _)
        assertEquals(from(ex).eval, actual)
    }
}


object TestRandomAccessReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(Traversal.RandomAccess, actual.traversal)
        impl(expected, actual)
    }

    def impl[A](expected: Array[A], actual: Rng[A]): Unit = {
        actual.assertModels(Traversal.RandomAccess)

        TestBidirectionalReadOnly.impl(expected, actual)
        TestRandomAccessReadablePointer(actual.begin, expected.length, expected)
    }
}
