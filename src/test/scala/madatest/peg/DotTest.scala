

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._


class DotTest {
    def testTrivial: Unit = {
        val sample = mada.vector.unstringize("/")
        assertTrue(dot[Char].matches(sample))
        assertFalse(dot[Char].matches(mada.vector.empty))
    }
}
