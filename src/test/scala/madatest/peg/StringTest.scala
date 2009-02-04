

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class StringTest {
    def testTrivial: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue(stringPeg("/*hello*/").matches(sample))
        assertFalse(stringPeg("/*hello*").matches(sample))
    }
}
