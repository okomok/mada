

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._


class DotTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val sample = mada.sequence.vector.from("/")
        assertTrue(dot.of[Char].matches(sample))
        assertFalse(dot.of[Char].matches(mada.sequence.vector.empty))
    }
}
