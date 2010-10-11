

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AdjacentTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val v = list.range(0, 4).asIterative
        val e = list.Of(List(0,1),List(1,2),List(2,3))
        assertEquals(e, v.adjacent(2))
    }

    def testEmpty {
        val v = list.empty
        assertTrue(v.adjacent(3).isEmpty)
    }

    def testOne {
        val v = list.Of(7)
        assertTrue(v.adjacent(2).isEmpty)
    }

    def testNonTrivial: Unit = {
        val t = List(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Vector[Int]]
        t.adjacent(4).foreach(out.add(_))
        assertEquals(List(List(1,2,3,4),List(2,3,4,5),List(3,4,5,6)), iterative.from(out))
    }

    def testWasteful: Unit = {
        val t = List(1,2,3)
        val out = new java.util.ArrayList[Vector[Int]]
        t.adjacent(1).foreach(out.add(_))
        assertEquals(List(List(1), List(2), List(3)), iterative.from(out))
    }
}
