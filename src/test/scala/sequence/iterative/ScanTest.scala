

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class ScanTest extends org.scalatest.junit.JUnit3Suite {
    def testScanLeft: Unit = {
    //    new NotStartable[Int].scanLeft(5)(_ + _)
        val t = iterative.Of(1,2,3,4,5,6,7,8)
        val u = iterative.Of(5,6,8,11,15,20,26,33,41)
        val k = t.scanLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testScanLeftX: Unit = {
        val t = iterative.Of(1)
        val u = iterative.Of(5,6)
        val k = t.scanLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        assertEquals(iterative.single(0), iterative.empty.of[Int].scanLeft(0)(_ + _))
    }

    def testScanLeft1: Unit = {
        val t = iterative.Of(5,1,2,3,4,5,6,7,8)
        val u = iterative.Of(5,6,8,11,15,20,26,33,41)
        val k = t.scanLeft1(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }
}
