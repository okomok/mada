

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._



class FilterTest {
    def testTrivial {
        val v = vector.range(0, 10)
        val e = vector.Of(0,2,4,6,8)
        assertEquals(e, v.copy.parallel.mutatingFilter(_ % 2 == 0))
    }

    def testRemove {
        val v = vector.range(0, 10)
        val e = vector.Of(0,2,4,6,8)
        assertEquals(e, v.copy.parallel.mutatingRemove(_ % 2 != 0))
    }

    def testFusion {
        val v = vector.range(0, 10)
        val e = vector.Of(0,4,6,8)
        assertEquals(e, v.copy.parallel.mutatingRemove(_ % 2 != 0).filter(_ != 2))
        assertEquals(e.reduce(_ + _), checkParallel(v.copy.parallel.mutatingRemove(_ % 2 != 0).copy.parallel.mutatingFilter(_ != 2)).reduce(_ + _))
    }

    def checkParallel[A](v: Vector[A]): Vector[A] = {
        //assertTrue(v.isParallel)
        v
    }
}
