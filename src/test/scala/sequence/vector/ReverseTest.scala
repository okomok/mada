

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import junit.framework.Assert._



class ReverseTest {
    def testTrivial: Unit = {
        assertEquals(mada.sequence.vector.from(Array(7,6,5,4,3,2)), vector.range(2, 8).reverse)
    }

    def testFusion: Unit = {
        assertEquals(mada.sequence.vector.from(Array(7,6,5,4,3,2)), vector.range(2, 8).reverse.reverse.reverse)
    }
}
