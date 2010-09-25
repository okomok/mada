

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class CloserTest extends org.scalatest.junit.JUnit3Suite {

    class MyResource extends reactive.Resource[Int] {
        private var out: Int => Unit = null
        var closed = false
        override protected def openResource(f: Int => Unit) = {
            out = f
        }
        override protected def closeResource = closed = true
        def generate(i: Int) = out(i)
    }

    def testTrivial {
        val r = new MyResource
        r.take(3).start
        r.generate(3)
        r.generate(3)
        assertFalse(r.closed)
        r.generate(3)
        assertTrue(r.closed)
    }

    def testChain {
        val r = new MyResource
        r.doing(_ => ()).filter(_ => true).take(3).start
        r.generate(3)
        r.generate(3)
        assertFalse(r.closed)
        r.generate(3)
        assertTrue(r.closed)
    }

    def testMerge {
        val l = new MyResource
        val r = new MyResource
        l.take(5).merge(r.take(8)).take(3).start
        l.generate(3)
        r.generate(3)
        assertFalse(l.closed)
        assertFalse(r.closed)
        r.generate(3)
        assertTrue(l.closed)
        assertTrue(r.closed)
    }

    def testFork {
        val r = new MyResource
        r.fork{s => s.take(5).start}.take(3).start
        r.generate(3)
        r.generate(3)
        r.generate(3)
        assertTrue(r.closed) // compromise
    }

    def testZip {
        val l = new MyResource
        val r = new MyResource
        l.take(5).zip(r.take(8)).take(3).start
        l.generate(3); r.generate(3)
        l.generate(3); r.generate(3)
        assertFalse(l.closed)
        assertFalse(r.closed)
        l.generate(3); r.generate(3)
        assertTrue(l.closed)
        assertTrue(r.closed)
    }

    def testZipBy {
        val l = new MyResource
        val r = new MyResource
        l.take(5).zipBy(r.take(8))((x,y)=>x+y).take(3).start
        l.generate(3); r.generate(3)
        l.generate(3); r.generate(3)
        assertFalse(l.closed)
        assertFalse(r.closed)
        l.generate(3); r.generate(3)
        assertTrue(l.closed)
        assertTrue(r.closed)
    }

}
