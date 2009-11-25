

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class TryTest {
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8,9)

        var finalOk = false

        val out = new java.util.ArrayList[Int]

        reactive.`try` {
            t.
            filter { _ > 3 }
        } `catch` {
            case x: AssertionError => out.add(88)
        } `finally` {
            finalOk = true
        } map { e =>
            if (e == 8) {
                throw new AssertionError
            } else {
                e + 10
            }
        } foreach { e =>
            out.add(e)
        }

        assertTrue(finalOk)
        assertEquals(iterative.Of(14,15,16,17,88,19), iterative.from(out))
    }
}
