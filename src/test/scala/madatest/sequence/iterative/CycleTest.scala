

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class CycleTest {
    def testCycle: Unit = {
        new NotStartable[Int].cycle
        val A1 = iterative.Of(1,6,7,10,14,17).cycle
        assertEquals(A1.take(18), iterative.Of(1,6,7,10,14,17,1,6,7,10,14,17,1,6,7,10,14,17))
        assertEquals(A1.take(18), iterative.Of(1,6,7,10,14,17,1,6,7,10,14,17,1,6,7,10,14,17)) // run again.
    }
/*
    unsupported
    def testCycleEmpty: Unit = {
        val A1 = iterative.emptyOf[Int]
        val A2 = iterative.Of(1,2,3)

        val I1 = A1.cycle.take(0)
        assertTrue(I1.isEmpty)
        assertTrue(I1.isEmpty) // run again.
    }
*/
    def testTimes: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17).times(3)
        assertEquals(A1, iterative.Of(1,6,7,10,14,17,1,6,7,10,14,17,1,6,7,10,14,17))
        assertEquals(A1, iterative.Of(1,6,7,10,14,17,1,6,7,10,14,17,1,6,7,10,14,17)) // run again.
    }

    def testTimesEmpty: Unit = {
        val A1 = iterative.emptyOf[Int]
        val A2 = iterative.Of(1,2,3)

        val I1 = A1.times(0)
        assertTrue(I1.isEmpty)
        assertTrue(I1.isEmpty) // run again.

        val I2 = A1.times(10)
        assertTrue(I2.isEmpty)
        assertTrue(I2.isEmpty) // run again.

        val I3 = A2.times(0)
        assertTrue(I3.isEmpty)
        assertTrue(I3.isEmpty) // run again.
    }

    def testSingle: Unit = {
        val A1 = iterative.single(9)
        val I1 = A1.cycle
        assertEquals(I1.take(3), iterative.Of(9,9,9))
        assertEquals(I1.take(3), iterative.Of(9,9,9))
        val I2 = A1.times(3)
        assertEquals(I2, iterative.Of(9,9,9))
        assertEquals(I2, iterative.Of(9,9,9))
    }
}
