
package madatest.rng.detail


import mada.rng.Rng._
import junit.framework.Assert._


class SortTest {
    def expected = from(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23)).eval
    def example = Array (0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)

    def testSelection {
        val a = example
        SelectionSort(from(a).eval)
        assertEquals(expected, from(a).eval)
    }

    def testBubble {
        val a = example
        BubbleSort(from(a).eval)
        assertEquals(expected, from(a).eval)
    }

    def testComb {
        val a = example
        CombSort(from(a).eval)
        assertEquals(expected, from(a).eval)
    }
}
