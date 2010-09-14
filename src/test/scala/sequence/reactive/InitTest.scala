

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class InitTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val t = reactive.Of(4,5,1,3,2,9,7,10)
        val k = t.init
        assertEquals(iterative.Of(4,5,1,3,2,9,7), k.toIterative)
    }

    def testEmpty {
        val t = reactive.empty
        val k = t.init
        assertTrue(k.isEmpty)
    }

}
