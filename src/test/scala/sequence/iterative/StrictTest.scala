

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class StrictTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val k = new java.util.ArrayList[Int]
        val tr = iterative.Of(2,4,6,8,10)
        val s = tr.strict.filter{ e => k.add(e); true }.map{ e => k.add(e); e }
        assertEquals(10, k.size)
        assertEquals(4, k.get(6))

        assertEquals(s, s.toVector)
        assertEquals(10, k.size)
        assertEquals(4, k.get(6))

        assertEquals(s, s.toVector)
        assertEquals(10, k.size)
        assertEquals(4, k.get(6))
    }
}
