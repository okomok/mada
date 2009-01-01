

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._


class StarTest {
    def testStar: Unit = {
        val sample = mada.Vector.stringVector("aaaaaaa")
        assertTrue((fromString("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.Vector.stringVector("aaaaaaab")
        assertTrue((fromString("a").* ~ fromString("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.Vector.stringVector("b")
        assertTrue((fromString("a").* ~ fromString("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.Vector.stringVector("Aabababab")
        assertTrue(  (fromString("A") ~ (fromString("a") ~ fromString("b")).* ).matches(sample)  ) // `.` is needed.
    }

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
