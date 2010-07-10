

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._


class SeqAndTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val sample = mada.sequence.vector.from("/*hello*/")
        assertTrue((from("/*hel") >> from("lo*/")).matches(sample))
        assertFalse((from("/*hel") >> from("lo*")).matches(sample))
    }
}
