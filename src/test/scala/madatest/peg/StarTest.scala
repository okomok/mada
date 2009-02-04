

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Pegs._
import junit.framework.Assert._


class StarTest {
    def testStar: Unit = {
        val sample = mada.Vectors.fromString("aaaaaaa")
        assertTrue((fromString("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.Vectors.fromString("aaaaaaab")
        assertTrue((fromString("a").* >> fromString("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.Vectors.fromString("b")
        assertTrue((fromString("a").* >> fromString("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.Vectors.fromString("Aabababab")
        assertTrue(  (fromString("A") >> (fromString("a") >> fromString("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((fromString("/*") >> (any *? fromString("*/")) >> fromString("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((fromString("/*") >> (any *>> fromString("*/"))).matches(sample))
    }

    def testBefore2: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((fromString("/*") >> __*?(fromString("*/")) >> fromString("*/")).matches(sample) )
    }

    def testUntil2: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((fromString("/*") >> __*>>(fromString("*/"))).matches(sample))
    }
}
