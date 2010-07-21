

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class CycleTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val L = (1 :: 2 :: 3 :: Nil).cycle
        val A = 1 :: 2 :: 3 :: 1 :: 2 :: 3 :: 1 :: 2 :: Nil
        assertEquals(A, L.take(8))
        assertEquals(A, L.take(8))
    }
}
