

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.detail


import mada.vec.From._
import mada.vec.Vector
import junit.framework.Assert._


object TestVectorReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Vector[A]): Unit = {
        TestVectorReadOnly(expected, actual)

        CombSort(actual)
        val ex = CopyArray(expected); CombSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)
    }
}


object TestVectorReadOnly {
    def apply[A](expected: Array[A], actual: Vector[A]): Unit = {
        assertEquals(expected.length, actual.size)

        var i = 0
        while (i < expected.size) {
            assertEquals(expected(i), actual(i))
            i += 1
        }
    }
}
