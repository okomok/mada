

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.Of("123", "12", "12345").map(_.length).subscribe(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(3,2,5, 99), vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[String].map(_.length).subscribe(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(99), vector.from(s))
    }
}
