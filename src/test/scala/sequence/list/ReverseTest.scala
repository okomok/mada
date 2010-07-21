

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ReverseTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        assertEquals(list.Of(7,6,5,4,3,2), vector.range(2, 8).toList.reverse)
    }

    def testRevRev: Unit = {
        assertEquals(list.Of(7,6,5,4,3,2), vector.range(2, 8).toList.reverse.reverse.reverse)
    }
}
