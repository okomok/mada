

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._


class AnyTest {
    def testTrivial: Unit = {
        val sample = mada.vector.unstringize("/")
        assertTrue(any[Char].matches(sample))
        assertFalse(any[Char].matches(mada.vector.empty))
    }
}
