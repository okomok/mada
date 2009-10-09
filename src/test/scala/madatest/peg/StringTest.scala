

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package pegtest


import mada.peg._
import junit.framework.Assert._


class StringTest {
    def testTrivial: Unit = {
        val sample = mada.sequence.vector.unstringize("/*hello*/")
        assertTrue(unstringize("/*hello*/").matches(sample))
        assertFalse(unstringize("/*hello*").matches(sample))
    }
}
