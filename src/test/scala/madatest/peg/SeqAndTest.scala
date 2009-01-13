

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class SeqAndTest {
    def testTrivial: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((stringPeg("/*hel") >> stringPeg("lo*/")).matches(sample))
        assertFalse((stringPeg("/*hel") >> stringPeg("lo*")).matches(sample))
    }
}