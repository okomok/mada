

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest.jcl


import mada.{Vector, vector}
import mada.vector.fromJclList
import madatest.vectortest.detail.NewArrayList
import junit.framework.Assert._


class ArrayListTest {
    def testTrivial {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = NewArrayList(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        assertNotSame(ex, ac)
        detail.TestVectorReadWrite(ex, fromJclList(ac))
    }
}
