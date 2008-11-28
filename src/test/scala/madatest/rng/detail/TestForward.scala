
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
        assertTrue(expected.length >= 2)

        TestForwardReadablePointer((actual.begin, expected(0)), (actual.begin.pre_++, expected(1)))

        val ex = from(expected).eval
        val w: Long = expected.length / 3;
        assertEquals(
            actual.begin <=< Search(actual, ex.begin.advance(w) <=< ex.begin.advance(2 * w)),
            ex.begin <=< Search(ex, ex.begin.advance(w) <=< ex.begin.advance(2 * w))
        )
    }
}
