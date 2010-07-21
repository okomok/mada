

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class NthTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17)
        assertEquals(1, A1.nth(0))
        assertEquals(10, A1.nth(3))
    }
}
