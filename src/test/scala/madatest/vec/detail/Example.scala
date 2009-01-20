

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.detail


import junit.framework.Assert._


object Example {
    def example1 = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4) // 15 elements
    def example1Sorted = Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23)
    def example1Reversed = Array(4,11,15,12, 0,23, 4, 6,13, 8,19,17,14,18, 0)
    def example1ReversedSorted = Array(23,19,18,17,15,14,13,12,11, 8, 6, 4, 4, 0, 0)
    def example2 = Array(0,18,14,17,19, 8,13, 6, 4,29, 0,12,15,11, 4) // 15 elements

    def empty1 = new Array[Int](0)

    def single(e: Int) = {
        val array = new Array[Int](1)
        array(0) = e
        array
    }
}


class ExampleTest {
    def testEmpty1 {
        assertEquals(0, Example.empty1.length)
    }

    def testSingle {
        val s = Example.single(99)
        assertEquals(1, s.length)
        assertEquals(99, s(0))
    }
}
