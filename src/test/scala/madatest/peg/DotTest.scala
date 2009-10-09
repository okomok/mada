

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package pegtest


import mada.peg._
import junit.framework.Assert._


class DotTest {
    def testTrivial: Unit = {
        val sample = mada.sequence.vector.unstringize("/")
        assertTrue(dot[Char].matches(sample))
        assertFalse(dot[Char].matches(mada.sequence.vector.empty))
    }
}
