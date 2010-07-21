

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class FilterTest extends junit.framework.TestCase {
    def testTrivial {
        val v = vector.range(0, 10).copy
        val e = vector.Of(0,2,4,6,8)
        assertEquals(e, v.filter(_ % 2 == 0))
    }

    def testRemove {
        val v = vector.range(0, 10).copy
        val e = vector.Of(0,2,4,6,8)
        assertEquals(e, v.remove(_ % 2 != 0))
    }

    def testFusion {
        val v = vector.range(0, 10).copy
        val e = vector.Of(0,4,6,8)
        assertEquals(e, v.remove(_ % 2 != 0).filter(_ != 2))
    }
}
