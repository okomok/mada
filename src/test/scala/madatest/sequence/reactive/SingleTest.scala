

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class SingleTest {
    def testTrivial: Unit = {
        val t = reactive.single(1)
        val s = new java.util.ArrayList[Int]
        t.subscribe(_ => s.add(99), s.add(_))
        assertEquals(vector.Of(1, 99), vector.from(s))
    }
}
