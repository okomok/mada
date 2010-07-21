

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class UniqueTest extends org.scalatest.junit.JUnit3Suite {

    def testUnique: Unit = {
    //    new NotStartable[Int].unique
        val tr = iterative.Of(5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique
        assertEquals(iterative.Of(5,4,3,2,1), sr)
        assertEquals(iterative.Of(5,4,3,2,1), sr) // traverse again.
    }

    def testFusion: Unit = {
        val tr = iterative.Of(5,5,5,4,4,4,3,2,2,2,2,2,1)
        val sr = tr.unique.unique.unique
        assertEquals(iterative.Of(5,4,3,2,1), sr)
        assertEquals(iterative.Of(5,4,3,2,1), sr) // traverse again.
    }

    def testUnique0: Unit = {
        val tr = iterative.empty.of[Int]
        val sr = tr.unique
        assertTrue(tr.isEmpty)
    }

    def testUnique1: Unit = {
        val tr = iterative.Of(9)
        val sr = tr.unique
        assertEquals(iterative.Of(9), sr)
        assertEquals(iterative.Of(9), sr) // traverse again.
    }

    def testUnique2: Unit = {
        val tr = iterative.Of(9,9,9,9,9,9)
        val sr = tr.unique
        assertEquals(iterative.Of(9), sr)
        assertEquals(iterative.Of(9), sr) // traverse again.
    }

}
