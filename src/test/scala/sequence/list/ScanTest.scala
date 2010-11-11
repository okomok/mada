

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence.list
import junit.framework.Assert._


class ScanTest extends org.scalatest.junit.JUnit3Suite {
// left
    def testScanLeft: Unit = {
        val t = list.Of(1,2,3,4,5,6,7,8)
        val u = list.Of(5,6,8,11,15,20,26,33,41)
        val k = t.scanLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testScanLeftX: Unit = {
        val t = list.Of(1)
        val u = list.Of(5,6)
        val k = t.scanLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        assertEquals(list.single(0), list.empty.of[Int].scanLeft(0)(_ + _))
    }

    def testScanLeft1: Unit = {
        val t = list.Of(5,1,2,3,4,5,6,7,8)
        val u = list.Of(5,6,8,11,15,20,26,33,41)
        val k = t.scanLeft1(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

// right
    def testScanRight: Unit = {
        val L = list.Of(1,2,3,4)
        val A = list.Of(15,14,12,9,5)
        assertEquals(A, L.scanRight(5)(_ + _()))
    }

    def testScanRight1: Unit = {
        val L = list.Of(1,2,3,4)
        val A = list.Of(10,9,7,4)
        assertEquals(A, L.scanRight1(_ + _()))
    }

    def testScanRightInfinite: Unit = {
        val L = list.Of(false,false,true,false).cycle
        val A = list.repeat(true)
        val L_ = L.scanRight(false)(_ || _()).take(50)
        assertEquals(A.take(50), L_)
        ()
    }

    def testScanRight1Infinite: Unit = {
        val L = list.Of(false,false,true,false).cycle
        val A = list.repeat(true)
        assertEquals(A.take(50), L.scanRight1(_ || _()).take(50))
    }

}
