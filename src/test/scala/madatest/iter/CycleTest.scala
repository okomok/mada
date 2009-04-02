

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.iter


import mada.Iterables
import junit.framework.Assert._


class CycleTest {
    def testTrivial: Unit = {
        val A1 = Iterables.cycle(Iterables(1,6,7,10,14,17), 3)
        assertTrue( Iterables.equal(A1, Iterables(1,6,7,10,14,17,1,6,7,10,14,17,1,6,7,10,14,17)) )
        assertTrue( Iterables.equal(A1, Iterables(1,6,7,10,14,17,1,6,7,10,14,17,1,6,7,10,14,17)) ) // run again.
    }

    def testEmpty: Unit = {
        val A1 = Iterables.emptyOf[Int]
        val A2 = Iterables(1,2,3)

        val I1 = Iterables.cycle(A1, 0)
        assertTrue(I1.isEmpty)
        assertTrue(I1.isEmpty) // run again.

        val I2 = Iterables.cycle(A1, 10)
        assertTrue(I2.isEmpty)
        assertTrue(I2.isEmpty) // run again.

        val I3 = Iterables.cycle(A2, 0)
        assertTrue(I3.isEmpty)
        assertTrue(I3.isEmpty) // run again.
    }
}
