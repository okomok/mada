
package madatest.rng.detail


import mada.rng.From._
import mada.rng.Rng
import junit.framework.Assert._


object TestForwardReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]) {
        TestForwardReadOnly(expected, actual)

        SelectionSort(actual)
        val ex = CopyArray(expected); SelectionSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)
    }
}


object TestForwardReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]) {
        val p = actual.begin
        TestForwardReadablePointer((p, expected(0)), (p.copy.pre_++, expected(1)))
    }
}
