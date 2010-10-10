

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._
import mada.sequence.iterative


class MultiTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val out = new java.util.ArrayList[Int]
        val rs = new java.util.ArrayList[Int => Unit]
        rs.add(x => out.add(x+10))
        rs.add(x => out.add(x+20))
        val t = reactive.Of(4,5,1,3)
        val k_ = t.foreach(reactive.multi(rs))
        assertEquals(vector.Of(14,24,15,25,11,21,13,23), vector.from(out))
    }

    def testReactive {
        val out = new java.util.ArrayList[Int]
        val rs = new java.util.ArrayList[Int => Unit]
        rs.add(x => out.add(x+10))
        rs.add(x => out.add(x+20))
        val t = reactive.Of(4,5,1,3)
        val k_ = t.foreach(reactive.multi(reactive.from(iterative.from(rs))))
        assertEquals(vector.Of(14,24,15,25,11,21,13,23), vector.from(out))
    }

}
