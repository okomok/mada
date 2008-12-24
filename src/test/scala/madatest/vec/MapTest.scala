

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class MapTest {
    def testTrivial {
        val v = Vector.range(0, 10)
        val e = Vector.range(1, 11)
        assertEquals(e, v.map(_ + 1))
    }
}
