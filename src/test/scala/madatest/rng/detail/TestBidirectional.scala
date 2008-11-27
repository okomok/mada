
package madatest.rng.detail


import mada.rng.From._
import mada.rng.Reverse._
import mada.rng.Rng
import junit.framework.Assert._


object NDebugReverse {
    var value: Boolean = true
}


object TestBidirectionalReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]) {
        TestBidirectionalReadOnly(expected, actual)

        BubbleSort(actual)
        val ex = CopyArray(expected); BubbleSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)
    }
}


object TestBidirectionalReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]) {
        TestBidirectionalReadablePointer(actual.begin, expected(0), expected(1))
        if (NDebugReverse.value)
            TestForwardReadOnly(CopyReverseArray(expected), actual.toExpr.rng_reverse.eval)
        TestForwardReadOnly(expected, actual)
    }
}
