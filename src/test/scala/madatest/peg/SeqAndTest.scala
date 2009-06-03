

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._


class SeqAndTest {
    def testTrivial: Unit = {
        val sample = mada.sequence.vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*hel") >> unstringize("lo*/")).matches(sample))
        assertFalse((unstringize("/*hel") >> unstringize("lo*")).matches(sample))
    }
}
