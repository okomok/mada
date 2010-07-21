

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector.from
import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._


class AppendTest extends junit.framework.TestCase {
    def testTrivial {
        val actual = from(Array(0,18,14,17)) ++ from(Array(19, 8,13, 6, 4,23, 0,12,15,11, 4))
        detail.TeztVectorReadWrite(example1, actual)
    }

    def testNontrivial {
        val actual = from(Array(0,18,14,17)) ++
            from(Array(19, 8,13, 6, 4)) ++ from(empty1) ++
            from(Array(23, 0,12,15)) ++ from(empty1) ++
            from(Array(11, 4)) ++ from(empty1) ++ from(empty1)
        detail.TeztVectorReadWrite(example1, actual)
    }

    def testEmpty {
        detail.TeztEmpty(from(empty1) ++ from(empty1))
        detail.TeztEmpty(from(empty1) ++ from(empty1) ++ from(empty1))
    }
}
