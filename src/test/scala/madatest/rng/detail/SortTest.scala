

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng.detail


import mada.rng.Rng._
import junit.framework.Assert._


class SortTest {
    def expected = from(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23)).eval
    def example =  from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)).eval

    def testSelection {
        val r = example
        SelectionSort(r)
        assertEquals(expected, r)
    }

    def testBubble {
        val r = example
        BubbleSort(r)
        assertEquals(expected, r)
    }

    def testComb {
        val r = example
        CombSort(r)
        assertEquals(expected, r)
    }
}
