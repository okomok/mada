
package madatest.rng.detail


import mada.rng.From._
import mada.rng.Reverse._
import mada.rng.Rng
import mada.rng._
import junit.framework.Assert._


object NDebugReverse {
    var value: Boolean = true
}


object TestBidirectionalReadWrite {
    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(BidirectionalTraversal, actual.traversal)
        impl(expected, actual)
    }

    def impl[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = {
        TestBidirectionalReadOnly.impl(expected, actual)

        BubbleSort(actual)
        val ex = CopyArray(expected); BubbleSort(from(ex).eval)
        assertEquals(from(ex).eval, actual)
    }
}


object TestBidirectionalReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(BidirectionalTraversal, actual.traversal)
        impl(expected, actual)
    }

    def impl[A](expected: Array[A], actual: Rng[A]): Unit = {
        AssertModels(actual, BidirectionalTraversal)

        TestForwardReadOnly.impl(expected, actual)
        TestBidirectionalReadablePointer(actual.begin, expected(0), expected(1))

        if (NDebugReverse.value)
            TestForwardReadOnly.impl(CopyReverseArray(expected), actual.toExpr.reverse.eval)
    }
}
