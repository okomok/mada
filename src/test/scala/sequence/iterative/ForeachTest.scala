

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class ForeachTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val tr = iterative.Of(1,2,3)
        val k1 = new java.util.ArrayList[Int]
        val k2 = new java.util.ArrayList[Int]
        tr.foreach(e => k1.add(e))
        k2.add(1); k2.add(2); k2.add(3)
        assertEquals(k1, k2)
    }

    def testEmpty: Unit = {
        val tr = iterative.empty.of[Int]
        val k1 = new java.util.ArrayList[Int]
        val k2 = new java.util.ArrayList[Int]
        tr.foreach(e => k1.add(e))
        assertEquals(k1, k2)
    }
}
