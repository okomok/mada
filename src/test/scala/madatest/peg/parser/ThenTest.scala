

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._


class ThenTest {
    def testTrivial: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((fromString("/*hel") ~ fromString("lo*/")).matches(sample))
        assertFalse((fromString("/*hel") ~ fromString("lo*")).matches(sample))
    }
}
