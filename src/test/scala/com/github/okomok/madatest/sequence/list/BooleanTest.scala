

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class BooleanTest {
    def testShortCircuit: Unit = {
        val L = (true #:: true #:: false #:: true #:: Nil) ::: (true #:: Nil).cycle
        assertFalse(L.and)
        assertTrue(L.or)
    }
}
