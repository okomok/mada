

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada._
import junit.framework.Assert._


class FindTest {
    def testFind {
        val v = Vector.range(2, 100)
        assertEquals(v.find((_: Int) == 30).get, 30)
    }

    def testExists {
        val v = Vector.range(2, 100)
        assertTrue(v.exists((_: Int) == 30))
        assertFalse(v.exists((_: Int) == 200))
    }

    def testForall {
        val v = Vector.range(2, 100)
        assertTrue(v.forall((_: Int) < 300))
        assertFalse(v.forall((_: Int) == 50))
    }
}
