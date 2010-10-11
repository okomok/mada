

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AdjacentTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val v = vector.range(0, 4).asIterative
        val e = iterative.Of((0,1),(1,2),(2,3))
        assertEquals(e, v.adjacent)
    }

    def testEmpty {
        val v = iterative.empty
        assertTrue(v.adjacent.isEmpty)
    }

    def testOne {
        val v = iterative.Of(7)
        assertTrue(v.adjacent.isEmpty)
    }

}
