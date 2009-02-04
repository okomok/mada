

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class StringTest {
    def testTrivial: Unit = {
        val sample = mada.Vector.fromString("/*hello*/")
        assertTrue(fromString("/*hello*/").matches(sample))
        assertFalse(fromString("/*hello*").matches(sample))
    }
}
