

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.vector
import junit.framework.Assert._


class IterativeTest {
    def testMerge: Unit = {
        val A1 = vector.Of(1,6,7,10,14,17)
        val A2 = vector.Of(2,5,8,11,13,18)
        val A3 = vector.Of(3,4,9,12,15,16)
        val AA = vector.Of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        val B1 = A1 merge A2 merge A3
        assertEquals(AA, B1)
        assertEquals(AA, B1) // run again.
    }
}
