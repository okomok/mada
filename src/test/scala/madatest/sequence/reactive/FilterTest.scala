

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class FilterTest {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.Of(0,1,2,3,4).filter(_ % 2 == 0).subscribe(reactor.make(s.add(99), s.add(_)))
        assertEquals(vector.Of(0,2,4, 99), vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].filter(_ % 2 == 0).subscribe(reactor.make(s.add(99), s.add(_)))
        assertEquals(vector.Of(99), vector.from(s))
    }
}
