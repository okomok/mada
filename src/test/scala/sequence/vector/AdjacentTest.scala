

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AdjacentTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val v = vector.range(0, 4)
        val e = vector.Of(Vector(0,1),Vector(1,2),Vector(2,3))
        assertEquals(e, v.adjacent(2))
    }

    def testEmpty {
        val v = vector.empty.of[Int]
        assertTrue(v.adjacent(3).isEmpty)
    }

    def testOne {
        val v = vector.Of(7)
        assertTrue(v.adjacent(2).isEmpty)
    }

    def testNonTrivial: Unit = {
        val t = vector.Of(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Vector[Int]]
        t.adjacent(4).foreach(out.add(_))
        assertEquals(vector.Of(Vector(1,2,3,4),Vector(2,3,4,5),Vector(3,4,5,6)), vector.from(out))
    }

    def testWasteful: Unit = {
        val t = vector.Of(1,2,3)
        val out = new java.util.ArrayList[Vector[Int]]
        t.adjacent(1).foreach(out.add(_))
        assertEquals(vector.Of(Vector(1),Vector(2),Vector(3)), vector.from(out))
    }

}
