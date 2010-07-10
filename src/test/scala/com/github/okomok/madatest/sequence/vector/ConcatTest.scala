

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector._
import junit.framework.Assert._


class ConcatTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val v1 = vector.from(Array(0,1,2))
        val v2 = vector.from(Array(3,4))
        val v3 = vector.from(detail.Example.empty1)
        val v4 = vector.from(Array(5,6))
        val v5 = vector.from(Array(7,8,9,10))
        val vv = vector.concat(v1,v2,v3,v4,v5)
        val e = vector.range(0, 11)
        detail.TeztVectorReadWrite(e.toArray, vv) // concat is a view; flatten is not.
    }

    def testEmpty: Unit = {
        val v1 = vector.from(detail.Example.empty1)
        val v2 = vector.from(detail.Example.empty1)
        val v3 = vector.from(detail.Example.empty1)
        val vv = vector.concat(v1,v2,v3)
        detail.TeztEmpty(vv)
    }

    def testEmpty2: Unit = {
        detail.TeztEmpty(vector.concat[Int]())
    }
}
