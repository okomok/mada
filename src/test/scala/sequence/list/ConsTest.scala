

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ConsTest extends junit.framework.TestCase {

    def testInfer: Unit = {
        val x: list.List[Int] = 1 :: 3 :: 4 :: Nil
        assertEquals(10, x.nth(1) + 7)
    }

    def testInferStrict: Unit = {
        val x: list.List[Int] = 1 #:: 3 #:: 4 #:: Nil
        assertEquals(10, x.nth(1) + 7)
    }

}
