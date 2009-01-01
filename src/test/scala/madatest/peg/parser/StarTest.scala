

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._


class StarTest {
    def testBefore: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((fromString("/*") ~ (any *? fromString("*/")) ~ fromString("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((fromString("/*") ~ (any *~ fromString("*/"))).matches(sample))
    }

    def testBefore2: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((fromString("/*") ~ __*?(fromString("*/")) ~ fromString("*/")).matches(sample) )
    }

    def testUntil2: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((fromString("/*") ~ __*~(fromString("*/"))).matches(sample))
    }
}
