

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class EndWithTest {
    def testTrivial: Unit = {
        val a = vector.Of(1,2,3,4,5,6)
        val b = new java.util.ArrayList[Int]

        reactive.fromIterative(a).takeWhile {
            _ <= 4
        } forLoop {
            b.add(_)
        } endWith {
            b.add(99)
        } endWith {
            b.add(999)
        } run

        assertEquals(vector.Of(1,2,3,4, 99, 999), vector.from(b))
    }
}
