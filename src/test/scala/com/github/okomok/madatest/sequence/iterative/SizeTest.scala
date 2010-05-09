

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class SizeTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val tr = iterative.Of(1,2,3)
        assertEquals(3, tr.size)
        assertEquals(3, tr.size)
    }

    def testEmpty: Unit = {
        val tr = iterative.empty.of[Int]
        assertEquals(0, tr.size)
        assertEquals(0, tr.size)
    }
}
