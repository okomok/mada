

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class StepTest {

    def testStep0: Unit = {
        // Unlike Vector, 0 is allowed.
        val tr = iterative.Of(1,2,3,4,5,6).toList.step(0)
        assertEquals(iterative.Of(1,1,1,1,1), tr.take(5))
        assertEquals(iterative.Of(1,1,1,1,1), tr.take(5))
    }

    def testStep1: Unit = {
        val tr = iterative.Of(1,2,3,4,5,6).toList.step(1)
        assertEquals(iterative.Of(1,2,3,4,5), tr.take(5))
        assertEquals(iterative.Of(1,2,3,4,5), tr.take(5))
    }

    def testStep2: Unit = {
        val tr = iterative.Of(1,2,3,4,5,6).toList.step(2)
        assertEquals(tr, iterative.Of(1,3,5))
    }

    def testStep3: Unit = {
        val tr = iterative.Of(1,2,3,4,5,6).toList.step(3)
        assertEquals(tr, iterative.Of(1,4))
    }

    def testStepStep: Unit = {
        val tr = iterative.Of(1,2,3,4,5,6,7,8,9,10,11).toList.step(3).step(2)
        assertEquals(tr, iterative.Of(1,7))
    }

    def testStepEmpty: Unit = {
        val tr0 = Nil.of[Int].step(0)
        assertTrue(tr0.isEmpty)
        val tr1 = Nil.of[Int].step(1)
        assertTrue(tr1.isEmpty)
        val tr2 = Nil.of[Int].step(2)
        assertTrue(tr2.isEmpty)
    }

}
