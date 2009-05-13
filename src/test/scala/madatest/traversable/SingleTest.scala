

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class SingleTest {
    def testTrivial: Unit = {
        val t = traversable.single(99)
        val u = traversable.fromValues(99)
        assertEquals(1, t.length)
        assertEquals(t, u)
        assertEquals(1, t.length)
        assertEquals(t, u)
    }
}
