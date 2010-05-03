

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class FlattenTest {
    def testTrivial {
        val v1 = vector.from(Array(0,1,2))
        val v2 = iterative.from(Array(3,4))
        val v3 = vector.from(detail.Example.empty1)
        val v4 = iterative.from(Array(5,6))
        val v5 = vector.from(Array(7,8,9,10))
        val vv = iterative.from(Array[iterative.Sequence[Int]](v1, v2, v3, v4, v5)).flatten.toVector
        val e = vector.range(0, 11)
        assertEquals(e, vv)
    }

    def testTrivial2 {
        val v1 = vector.from(Array(0,1,2))
        val v2 = iterative.from(Array(3,4))
        val v3 = vector.from(detail.Example.empty1)
        val v4 = iterative.from(Array(5,6))
        val v5 = vector.from(Array(7,8,9,10))
        val vv = vector.from(Array[iterative.Sequence[Int]](v1, v2, v3, v4, v5)).flatten.toVector
        val e = vector.range(0, 11)
        assertEquals(e, vv)
    }

    def testEmpty {
        val v1 = vector.from(detail.Example.empty1)
        val v2 = iterative.from(detail.Example.empty1)
        val v3 = vector.from(detail.Example.empty1)
        val vv = iterative.from(Array[iterative.Sequence[Int]](v1, v2, v3)).flatten.toVector
        detail.TestEmpty(vv)
    }

    def testFlatMap: Unit = {
        val v = vector.from(detail.Example.example1).flatMap(vector.single(_: Int)).toVector
        detail.TestVectorReadOnly(detail.Example.example1, v)
    }
}
