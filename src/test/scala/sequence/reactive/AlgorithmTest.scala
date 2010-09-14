

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AlgorithmTest extends org.scalatest.junit.JUnit3Suite {

    def testIsEmpty: Unit = {
        val r1 = reactive.Of(2,4,6,7,8)
        assertFalse(r1.isEmpty)
        val r2 = reactive.empty
        assertTrue(r2.isEmpty)
    }

    def testForall {
        val r1 = reactive.Of(2,4,6,8)
        assertTrue(r1.forall(_ % 2 == 0))
        assertFalse(r1.forall(_ == 3))
    }

    def testExists {
        val r1 = reactive.Of(1,3,6,1,2,0,1,3)
        assertTrue(r1.exists(_ == 0))
        assertFalse(r1.exists(_ == 9))
    }

    def testFind {
        val r1 = reactive.Of(1,3,5,1,0,13,2)
        val p = r1.find(_ == 0)
        assertFalse(p.isEmpty)
        assertEquals(0, p.get)
        val q = r1.find(_ == 9)
        assertTrue(q.isEmpty)
    }

    def testHead {
        val r1 = reactive.Of(3,6,11)
        assertEquals(3, r1.head)
    }

    def testLast {
        val r1 = reactive.Of(3,1,6)
        assertEquals(6, r1.last)
    }

}
