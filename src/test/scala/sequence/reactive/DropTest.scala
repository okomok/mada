

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence.reactive
import junit.framework.Assert._
import mada.sequence.iterative


class DropTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val t = reactive.Of(4,5,1,3,2,9,7,10)
        val k = t.drop(5)
        assertEquals(iterative.Of(9,7,10), k.toIterative)
        val k_ = t.drop(7)
        assertEquals(iterative.Of(10), k_.toIterative)
        assertTrue(t.drop(8).isEmpty)
        assertTrue(t.drop(9).isEmpty)
        assertTrue(t.drop(80).isEmpty)
    }

}
