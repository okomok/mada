

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class UnfoldTest extends org.scalatest.junit.JUnit3Suite {
    def testRight: Unit = {
        val r = reactive.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        val out = new java.util.ArrayList[Int]
        r.foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1), iterative.from(out))
    }

    def testThen: Unit = {
        val r = reactive.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        val out = new java.util.ArrayList[Int]
        r.then(out.add(99)).then(out.add(98)).foreach(out.add(_))
        r.generateAll
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1,99,98), iterative.from(out))
    }

    def testIterate: Unit = {
        val br = new scala.util.control.Breaks
        import br._
        val r = reactive.iterate(10){ b => b-1 }
        val out = new java.util.ArrayList[Int]
        r.take(10).foreach(out.add(_))
        r.generate(15)
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1), iterative.from(out))
    }

    def testRepeat: Unit = {
        val br = new scala.util.control.Breaks
        import br._
        val r = reactive.repeat(10)
        val out = new java.util.ArrayList[Int]
        r.take(5).foreach(out.add(_))
        r.generate(15)
        assertEquals(iterative.Of(10,10,10,10,10), iterative.from(out))
    }
}
