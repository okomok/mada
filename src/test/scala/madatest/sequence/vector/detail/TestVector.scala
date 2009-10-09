

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest; package detail


import mada.sequence._
import junit.framework.Assert._


object TestVectorReadWrite {
    def apply[A<% Ordered[A] : ClassManifest](expected: Array[A], actual: Vector[A]): Unit = {
        TestVectorReadOnly(expected, actual)

        CombSort(actual)
        val ex = CopyArray(expected); CombSort(vector.fromArray(ex))
        assertEquals(vector.fromArray(ex), actual)
    }
}


object TestVectorReadOnly {
    def apply[A](expected: Array[A], actual: Vector[A]): Unit = {
        assertEquals(expected.length, actual.size)

        var first = actual.start
        var i = 0
        while (i < expected.size) {
            assertTrue(actual.isDefinedAt(first))
            assertEquals(expected(i), actual(first))
            i += 1
            first += 1
        }
        assertEquals(actual.end, first)
    }
}
