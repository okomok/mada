

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import junit.framework.Assert._


class OrderedTest {
    def testTrivial {
        val v1 = Vector.from("ab")
        val v2 = Vector.from("")
        val v3 = Vector.from("abc")
        val v4 = Vector.from("aec")
        val v5 = Vector.from("b")
        val v6 = Vector.from("abd")
        val vv = Vector(v1, v2, v3, v4, v5, v6)
        vv.sort//(Vector.orderedView[Char])
        val ww = Vector(v2, v1, v3, v6, v4, v5)
        assertEquals(ww, vv)
    }
}
