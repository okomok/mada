

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._


class StarTest {
    def testStar: Unit = {
        val sample = mada.Vector.stringVector("aaaaaaa")
        assertTrue((stringParser("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.Vector.stringVector("aaaaaaab")
        assertTrue((stringParser("a").* ~ stringParser("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.Vector.stringVector("b")
        assertTrue((stringParser("a").* ~ stringParser("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.Vector.stringVector("Aabababab")
        assertTrue(  (stringParser("A") ~ (stringParser("a") ~ stringParser("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((stringParser("/*") ~ (any *? stringParser("*/")) ~ stringParser("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((stringParser("/*") ~ (any *~ stringParser("*/"))).matches(sample))
    }

    def testBefore2: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((stringParser("/*") ~ __*?(stringParser("*/")) ~ stringParser("*/")).matches(sample) )
    }

    def testUntil2: Unit = {
        val sample = mada.Vector.stringVector("/*hello*/")
        assertTrue((stringParser("/*") ~ __*~(stringParser("*/"))).matches(sample))
    }
}
