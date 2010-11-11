

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.{Iterative, iterative}
import junit.framework.Assert._


class AccessTest extends org.scalatest.junit.JUnit3Suite {
    def testHeadLast: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17)
        assertEquals(1, A1.head)
        assertEquals(17, A1.last)

        val A2 = iterative.Of(5)
        assertEquals(5, A2.head)
        assertEquals(5, A2.last)
    }

    def testHeadLastOption: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17)
        assertEquals(1, mada.util.optional(A1.head).get)
        assertEquals(17, mada.util.optional(A1.last).get)

        val A2 = iterative.empty.of[Int]
        assertEquals(None, mada.util.optional(A2.head))
        assertEquals(None, mada.util.optional(A2.last))
    }
}
