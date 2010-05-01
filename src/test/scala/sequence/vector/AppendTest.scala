

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector.fromArray
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._


class AppendTest {
    def testTrivial {
        val actual = fromArray(Array(0,18,14,17)) ++ fromArray(Array(19, 8,13, 6, 4,23, 0,12,15,11, 4))
        detail.TestVectorReadWrite(example1, actual)
    }

    def testNontrivial {
        val actual = fromArray(Array(0,18,14,17)) ++
            fromArray(Array(19, 8,13, 6, 4)) ++ fromArray(empty1) ++
            fromArray(Array(23, 0,12,15)) ++ fromArray(empty1) ++
            fromArray(Array(11, 4)) ++ fromArray(empty1) ++ fromArray(empty1)
        detail.TestVectorReadWrite(example1, actual)
    }

    def testEmpty {
        detail.TestEmpty(fromArray(empty1) ++ fromArray(empty1))
        detail.TestEmpty(fromArray(empty1) ++ fromArray(empty1) ++ fromArray(empty1))
    }
}
