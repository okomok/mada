

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._


class ConcatTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val v1 = vector.fromArray(Array(0,1,2))
        val v2 = vector.fromArray(Array(3,4))
        val v3 = vector.fromArray(detail.Example.empty1)
        val v4 = vector.fromArray(Array(5,6))
        val v5 = vector.fromArray(Array(7,8,9,10))
        val vv = vector.concat(v1,v2,v3,v4,v5)
        val e = vector.range(0, 11)
        detail.TestVectorReadWrite(e.toArray, vv) // concat is a view; flatten is not.
    }

    def testEmpty: Unit = {
        val v1 = vector.fromArray(detail.Example.empty1)
        val v2 = vector.fromArray(detail.Example.empty1)
        val v3 = vector.fromArray(detail.Example.empty1)
        val vv = vector.concat(v1,v2,v3)
        detail.TestEmpty(vv)
    }

    def testEmpty2: Unit = {
        detail.TestEmpty(vector.concat[Int]())
    }
}
