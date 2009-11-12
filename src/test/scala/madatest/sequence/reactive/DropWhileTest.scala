

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class DropWhileTest {
    def testTrivial0: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]
        reactive.fromIterative(a).dropWhile(_ <= 4).subscribe(reactor.make(b.add(99), b.add(_)))
        assertEquals(vector.Of(5,6,99), vector.from(b))
    }
}
