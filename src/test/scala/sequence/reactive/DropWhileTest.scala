

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class DropWhileTest extends junit.framework.TestCase {
    def testTrivial0: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).dropWhile(_ <= 4).activate(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(5,6,99), vector.from(b))
    }

    def testAll: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).dropWhile(_ <= 10).activate(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(99), vector.from(b))
    }

    def testEmpty: Unit = {
        val a = vector.empty[Int]
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).dropWhile(_ <= 10).activate(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(99), vector.from(b))
    }

    def testNone: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).dropWhile(_ > 10).activate(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(1,2,3,4,5,6,99), vector.from(b))
    }
}
