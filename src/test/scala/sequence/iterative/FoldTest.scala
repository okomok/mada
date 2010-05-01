

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class FoldTest {
    def testFoldLeft: Unit = {
        val tr = iterative.Of(2,4,6)
        assertEquals(1+2+4+6, tr.foldLeft(1)(_ + _))
    }

    def testFoldLeft0: Unit = {
        val tr = iterative.empty.of[Int]
        assertEquals(1, tr.foldLeft(1)(_ + _))
    }

    def testReduceLeft: Unit = {
        val tr = iterative.Of(2,4,6)
        assertEquals(2+4+6, tr.reduceLeft(_ + _))
    }
}
