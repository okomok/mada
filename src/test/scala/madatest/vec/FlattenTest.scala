

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada._
import junit.framework.Assert._


class FlattenTest {
    def testTrivial {
        val v1 = Vectors.fromArray(Array(0,1,2))
        val v2 = Vectors.fromArray(Array(3,4))
        val v3 = Vectors.fromArray(detail.Example.empty1)
        val v4 = Vectors.fromArray(Array(5,6))
        val v5 = Vectors.fromArray(Array(7,8,9,10))
        val vv = Vectors.flatten(Vectors.fromArray(Array(v1, v2, v3, v4, v5)))
        val e = Vectors.range(0, 11)
        assertEquals(e, vv)
    }

    def testEmpty {
        val v1 = Vectors.fromArray(detail.Example.empty1)
        val v2 = Vectors.fromArray(detail.Example.empty1)
        val v3 = Vectors.fromArray(detail.Example.empty1)
        val vv = Vectors.flatten(Vectors.fromArray(Array(v1, v2, v3)))
        detail.TestEmpty(vv)
    }

    def testFlatMap: Unit = {
        val v = Vectors.fromArray(detail.Example.example1).flatMap(Vectors.single(_: Int))
        detail.TestVectorReadOnly(detail.Example.example1, v)
    }
}
