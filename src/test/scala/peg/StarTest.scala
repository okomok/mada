

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._


class StarTest extends junit.framework.TestCase {
    def testStar: Unit = {
        val sample = mada.sequence.vector.from("aaaaaaa")
        assertTrue((from("a")*).matches(sample))
    }

    def testStar2: Unit = {
        val sample = mada.sequence.vector.from("aaaaaaab")
        assertTrue((from("a").* >> from("b")).matches(sample))
    }

    def testStar3: Unit = {
        val sample = mada.sequence.vector.from("b")
        assertTrue((from("a").* >> from("b")).matches(sample))
    }

    def testStar4: Unit = {
        val sample = mada.sequence.vector.from("Aabababab")
        assertTrue(  (from("A") >> (from("a") >> from("b")).* ).matches(sample)  ) // `.` is needed.
    }

    def testBefore: Unit = {
        val sample = mada.sequence.vector.from("/*hello*/")
        assertTrue((from("/*") >> (dot.* >>> ~from("*/")) >> from("*/")).matches(sample))
    }

    def testUntil: Unit = {
        val sample = mada.sequence.vector.from("/*hello*/")
        assertTrue((from("/*") >> (dot.* >>> from("*/"))).matches(sample))
    }
}
