

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class SeqAndTest {
    def testTrivial: Unit = {
        val sample = mada.Vector.fromString("/*hello*/")
        assertTrue((fromString("/*hel") >> fromString("lo*/")).matches(sample))
        assertFalse((fromString("/*hel") >> fromString("lo*")).matches(sample))
    }
}
