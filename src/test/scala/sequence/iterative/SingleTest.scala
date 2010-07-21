

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class SingleTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = iterative.single(99)
        val u = iterative.Of(99)
        assertEquals(1, t.size)
        assertEquals(t, u)
        assertEquals(1, t.size)
        assertEquals(t, u)
    }
}
