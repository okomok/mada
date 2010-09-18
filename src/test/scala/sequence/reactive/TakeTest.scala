

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class TakeTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial0: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.from(a).take(3).foreach(b.add(_))
        assertEquals(vector.Of(1,2,3), vector.from(b))
    }

    def testTrivial {
        val t = reactive.Of(4,5,1,3,2,9,7,10)
        val k = t.take(5)
        assertEquals(iterative.Of(4,5,1,3,2), k.toIterative)
        val k_ = t.take(50)
        assertEquals(iterative.Of(4,5,1,3,2,9,7,10), k_.toIterative)
        assertTrue(t.take(0).toIterative.isEmpty)
    }

}
