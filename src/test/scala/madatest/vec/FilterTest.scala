

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class FilterTest {
    def testTrivial {
        val v = Vector.range(0, 10).clone
        val e = Vector.fromValues(0,2,4,6,8)
        assertEquals(e, v.filter(_ % 2 == 0))
    }

    def testRemove {
        val v = Vector.range(0, 10).clone
        val e = Vector.fromValues(0,2,4,6,8)
        assertEquals(e, v.remove(_ % 2 != 0))
    }

    def testFusion {
        val v = Vector.range(0, 10).clone
        val e = Vector.fromValues(0,4,6,8)
        assertEquals(e, v.remove(_ % 2 != 0).filter(_ != 2))
    }
}
