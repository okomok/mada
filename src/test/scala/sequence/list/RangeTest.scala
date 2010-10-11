

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class RangeTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        assertEquals(list.Of(1,2,3,4), list.range(1, 5))
    }
    def testEmpty: Unit = {
        assertTrue(list.range(1, 1).isEmpty)
    }
}
