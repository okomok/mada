

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence._
import junit.framework.Assert._


class FlattenTest {
    def testTrivial {
        val v1 = iterative.from(Array(0,1,2))
        val v2 = iterative.from(Array(3,4))
        val v3 = iterative.from(detail.Example.empty1)
        val v4 = iterative.from(Array(5,6))
        val v5 = iterative.from(Array(7,8,9,10))
        val vv = vector.flatten(iterative.fromSIterable[Iterative[Int]](Array(v1, v2, v3, v4, v5)))
        val e = vector.range(0, 11)
        assertEquals(e, vv)
    }

    def testEmpty {
        val v1 = iterative.from(detail.Example.empty1)
        val v2 = iterative.from(detail.Example.empty1)
        val v3 = iterative.from(detail.Example.empty1)
        val vv = vector.flatten(iterative.fromSIterable[Iterative[Int]](Array(v1, v2, v3)))
        detail.TestEmpty(vv)
    }

    def testFlatMap: Unit = {
        val v = vector.from(detail.Example.example1).flatMap(vector.single(_: Int))
        detail.TestVectorReadOnly(detail.Example.example1, v)
    }
}
