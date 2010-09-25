

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class RangeTest extends org.scalatest.junit.JUnit3Suite {
    def testEmpty: Unit = {
        val r = reactive.range(1,1)
        val out = new java.util.ArrayList[Int]
        r.foreach(out.add(_))
        r.generateAll
        assertTrue(iterative.from(out).isEmpty)
    }

    def testTrivial: Unit = {
        val r = reactive.range(1,6)
        val out = new java.util.ArrayList[Int]
        r.foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(1,2,3,4,5), iterative.from(out))
    }

    def testThen: Unit = {
        val r = reactive.range(1,6)
        val out = new java.util.ArrayList[Int]
        r.then(out.add(99)).then(out.add(98)).foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(1,2,3,4,5,99,98), iterative.from(out))
    }
}
