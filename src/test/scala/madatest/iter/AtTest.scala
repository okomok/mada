

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.iter


import mada.Iterables
import junit.framework.Assert._


class AtTest {
    def testTrivial: Unit = {
        val A1 = Iterables(1,6,7,10,14,17)
        assertEquals(1, Iterables.at(A1, 0))
        assertEquals(10, Iterables.at(A1, 3))
    }
}
