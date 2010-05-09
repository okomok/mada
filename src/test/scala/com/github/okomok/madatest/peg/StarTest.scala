

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._


class StarTest extends junit.framework.TestCase {
    def testStar: Unit = {
        val sample = mada.sequence.vector.unstringize("aaaaaaa")
        assertTrue((unstringize("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.sequence.vector.unstringize("aaaaaaab")
        assertTrue((unstringize("a").* >> unstringize("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.sequence.vector.unstringize("b")
        assertTrue((unstringize("a").* >> unstringize("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.sequence.vector.unstringize("Aabababab")
        assertTrue(  (unstringize("A") >> (unstringize("a") >> unstringize("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.sequence.vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*") >> (dot.* >>> ~unstringize("*/")) >> unstringize("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.sequence.vector.unstringize("/*hello*/")
        assertTrue((unstringize("/*") >> (dot.* >>> unstringize("*/"))).matches(sample))
    }
}
