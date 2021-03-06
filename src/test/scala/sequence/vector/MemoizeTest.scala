

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import junit.framework.Assert._


class MemoizeTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        var memo = false
        val v = vector.from(Array(0,1,2,3,4,5)).map{ x => if (memo) fail("doh"); x + 10 }
        val w = v.memoize
        assertEquals(vector.from(Array(10,11,12,13,14,15)), w)
        memo = true
        assertEquals(vector.from(Array(10,11,12,13,14,15)), w)
    }
}
