

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class TakeWhileTest {
    def testTrivial0: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).takeWhile(_ <= 4).start(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(1,2,3,4, 99), vector.from(b))
    }

    def testAll: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).takeWhile(_ <= 10).start(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(1,2,3,4,5,6, 99), vector.from(b))
    }

    def testEmpty: Unit = {
        val a = vector.empty[Int]
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).takeWhile(_ <= 10).start(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(99), vector.from(b))
    }

    def testNone: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).takeWhile(_ > 10).start(reactor.make(_ => b.add(99), b.add(_)))
        assertEquals(vector.Of(99), vector.from(b))
    }
}
