

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class AtTest {
    def testTrivial: Unit = {
        val A1 = sequence.Of(1,6,7,10,14,17)
        assertEquals(1, A1.at(0))
        assertEquals(10, A1.at(3))
    }
}
