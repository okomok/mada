

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence._
import junit.framework.Assert._


class CycleTest {
    def testTrivial: Unit = {
        val L = (1 :: 2 :: 3 :: Nil).cycle
        val A = 1 :: 2 :: 3 :: 1 :: 2 :: 3 :: 1 :: 2 :: Nil
        assertEquals(A, L.take(8))
        assertEquals(A, L.take(8))
    }
}
