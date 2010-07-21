

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence.list
import junit.framework.Assert._


class EmptyTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val tr = list.empty.of[Int]
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty)
    }
}
