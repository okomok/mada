

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector.from
import junit.framework.Assert._


class ValuesTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val ex = Array(5,1,3,6,9,7,10,0)
        val ac = vector.Of(5,1,3,6,9,7,10,0)
        detail.TeztVectorReadOnly(ex, ac)
    }

    def testEmpty: Unit = {
        val ac = vector.Of[Int]()
        detail.TeztEmpty(ac)
    }
}
