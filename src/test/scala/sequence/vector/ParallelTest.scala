

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ParallelTest extends org.scalatest.junit.JUnit3Suite {
    def testFusion: Unit = {
        val v = vector.range(0, 10)
        val e = vector.range(1, 11)
        assertEquals(e, v.parallelBy(4).parallelBy(3).parallelBy(1).map(_ + 1))
    }
/*
    def testUnparallel: Unit = {
        val v = vector.range(0, 10)
        val e = vector.range(1, 11)
        val pv = v.parallelBy(4).parallelBy(3)
        assertTrue(pv.isParallel)
        val upv = pv.unparallel
        assertFalse(upv.isParallel)
        val uupv = pv.unparallel.unparallel
        assertFalse(uupv.isParallel)
    }
*/
    def foo(x: Any): Unit = {
        //println(x)
        ()
    }

    def testParaPara: Unit = {
        for (i <- (0 until 100)) {
            // println(i)
            val z = vector.range(1, 10).parallel.map {
                case i => vector.range(1, i).copy.parallel.mutatingFilter{ j => i+j >= 5 }.parallel.map{ case j => (i, j) }
            }.flatten
            ()
        }
        ()
    }
}
        // java.lang.AssertionError: assertion failed: receive from channel belonging to other actor
        // http://www.nabble.com/Actors-Break-Futures-td13813999.html

