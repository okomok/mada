

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class FlattenTest {
    def testTrivial {
        val v1 = Vector.fromArray(Array(0,1,2))
        val v2 = Vector.fromArray(Array(3,4))
        val v3 = Vector.fromArray(detail.Example.empty1)
        val v4 = Vector.fromArray(Array(5,6))
        val v5 = Vector.fromArray(Array(7,8,9,10))
        val vv = Vector.flatten(Vector.fromArray(Array(v1, v2, v3, v4, v5)))
        val e = Vector.range(0, 11)
        assertEquals(e, vv)
    }

    def testEmpty {
        val v1 = Vector.fromArray(detail.Example.empty1)
        val v2 = Vector.fromArray(detail.Example.empty1)
        val v3 = Vector.fromArray(detail.Example.empty1)
        val vv = Vector.flatten(Vector.fromArray(Array(v1, v2, v3)))
        detail.TestEmpty(vv)
    }

    def testFlatMap: Unit = {
        val v = Vector.fromArray(detail.Example.example1).flatMap(Vector.single(_: Int))
        detail.TestVectorReadOnly(detail.Example.example1, v)
    }
}
