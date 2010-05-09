

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._


class DotTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val sample = mada.sequence.vector.unstringize("/")
        assertTrue(dot[Char].matches(sample))
        assertFalse(dot[Char].matches(mada.sequence.vector.empty))
    }
}
