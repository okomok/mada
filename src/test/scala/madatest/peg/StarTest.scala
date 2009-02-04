

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._


class StarTest {
    def testStar: Unit = {
        val sample = mada.Vectors.fromString("aaaaaaa")
        assertTrue((stringPeg("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.Vectors.fromString("aaaaaaab")
        assertTrue((stringPeg("a").* >> stringPeg("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.Vectors.fromString("b")
        assertTrue((stringPeg("a").* >> stringPeg("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.Vectors.fromString("Aabababab")
        assertTrue(  (stringPeg("A") >> (stringPeg("a") >> stringPeg("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((stringPeg("/*") >> (any *? stringPeg("*/")) >> stringPeg("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((stringPeg("/*") >> (any *>> stringPeg("*/"))).matches(sample))
    }

    def testBefore2: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((stringPeg("/*") >> __*?(stringPeg("*/")) >> stringPeg("*/")).matches(sample) )
    }

    def testUntil2: Unit = {
        val sample = mada.Vectors.fromString("/*hello*/")
        assertTrue((stringPeg("/*") >> __*>>(stringPeg("*/"))).matches(sample))
    }
}
