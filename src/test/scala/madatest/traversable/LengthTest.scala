

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class LengthTest {
    def testTrivial: Unit = {
        val tr = traversable.of(1,2,3)
        assertEquals(3, tr.length)
        assertEquals(3, tr.length)
    }

    def testEmpty: Unit = {
        val tr = traversable.emptyOf[Int]
        assertEquals(0, tr.length)
        assertEquals(0, tr.length)
    }
}
