

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


import mada.sequence.{Iterative, iterative}
import junit.framework.Assert._


class AccessTest {
    def testHeadLast: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17)
        assertEquals(1, A1.head)
        assertEquals(17, A1.last)

        val A2 = iterative.Of(5)
        assertEquals(5, A2.head)
        assertEquals(5, A2.last)
    }

    def testHeadLastOption: Unit = {
        val A1 = iterative.Of(1,6,7,10,14,17)
        assertEquals(1, A1.headOption.get)
        assertEquals(17, A1.lastOption.get)

        val A2 = iterative.emptyOf[Int]
        assertEquals(None, A2.headOption)
        assertEquals(None, A2.lastOption)
    }
}
