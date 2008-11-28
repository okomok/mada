
package madatest.rng.detail


import mada.rng.From._
import mada.rng.Rng
import junit.framework.Assert._


object TestRandomAccessReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]) {
        TestRandomAccessReadOnly(expected, actual)

        CombSort(actual)
        val ex = CopyArray(expected); CombSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)
    }
}


object TestRandomAccessReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]) {
        TestBidirectionalReadOnly(expected, actual)
        TestRandomAccessReadablePointer(actual.begin, expected.length, expected)
    }
}
