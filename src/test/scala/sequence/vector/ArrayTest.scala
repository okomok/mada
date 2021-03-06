

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector.from
import junit.framework.Assert._


class ArrayTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val a = from(Array.range(0, 6))
        val b = from(Array.range(0, 6))
        assertEquals(a, b)
    }

    def testWritable: Unit = {
        val a = Array.range(0, 6)
        a(0) = 99
        val v2 = from(Array.range(0, 6))
        v2(0) = 99
        assertEquals(from(a), v2)
    }

    def testMe: Unit = {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        assertNotSame(ex, ac)
        detail.TeztVectorReadWrite(ex, from(ac))
    }
}
