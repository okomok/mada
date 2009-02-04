

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Pegs._
import junit.framework.Assert._


class AnyTest {
    def testTrivial: Unit = {
        val sample = mada.Vectors.fromString("/")
        assertTrue(any[Char].matches(sample))
        assertFalse(any[Char].matches(mada.Vectors.empty))
    }
}
