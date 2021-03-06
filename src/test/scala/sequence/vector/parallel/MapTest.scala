

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class MapTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val v = vector.range(0, 10)
        val e = vector.range(1, 11)
        assertEquals(e, v.parallel.map(_ + 1))
        assertEquals(e, v.parallelBy(4).map(_ + 1))
        assertEquals(e, v.parallelBy(500).map(_ + 1))
    }

    def testMapMap: Unit = {
        val v = vector.range(0, 10)
        val e = vector.range(2, 12)
        for (i <- (0 until 3)) {
        assertEquals(e, v.parallel.map(_ + 1).parallel.map(_ + 1))
        assertEquals(e, v.parallelBy(4).map(_ + 1).parallel.map(_ + 1))
        assertEquals(e, v.parallelBy(500).map(_ + 1).parallel.map(_ + 1))
        ()
        }
    }

    def testFusion: Unit = { // In fact, no fusions.
        val v = vector.range(0, 10)
        val e = vector.range(2, 12)
        assertEquals(e, v.parallel.map(_ + 1).map(_ + 1))
        assertEquals(e, v.parallelBy(4).map(_ + 1).map(_ + 1))
        assertEquals(e, v.parallelBy(500).map(_ + 1).map(_ + 1))

        assertEquals(e.reduce(_ + _), v.parallel.map(_ + 1).map(_ + 1).reduce(_ + _))
        assertEquals(7, v.parallel.map(_ + 1).map(_ + 1).seek(_ == 7).get)
    }

    def testMany: Unit = {
        val v = vector.range(0, 10000)
        val e = vector.range(2, 10002)
        assertEquals(e, v.parallelBy(1).map{ x => x + 2 })
        assertEquals(e, v.parallelBy(3).map{ x => x + 2 } )
    }
}
