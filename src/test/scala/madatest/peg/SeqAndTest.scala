

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class SeqAndTest {
    def testTrivial: Unit = {
        val sample = mada.Vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*hel") >> unstringize("lo*/")).matches(sample))
        assertFalse((unstringize("/*hel") >> unstringize("lo*")).matches(sample))
    }
}
