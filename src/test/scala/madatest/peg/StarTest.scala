

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._


class StarTest {
    def testStar: Unit = {
        val sample = mada.vector.unstringize("aaaaaaa")
        assertTrue((unstringize("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.vector.unstringize("aaaaaaab")
        assertTrue((unstringize("a").* >> unstringize("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.vector.unstringize("b")
        assertTrue((unstringize("a").* >> unstringize("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.vector.unstringize("Aabababab")
        assertTrue(  (unstringize("A") >> (unstringize("a") >> unstringize("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*") >> (dot.* >>> ~unstringize("*/")) >> unstringize("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*") >> (dot.* >>> unstringize("*/"))).matches(sample))
    }
}
