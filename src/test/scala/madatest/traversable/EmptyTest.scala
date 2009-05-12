

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class EmptyTest {
    def testTrivial: Unit = {
        val tr = traversable.emptyOf[Int]
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty)
    }
}
