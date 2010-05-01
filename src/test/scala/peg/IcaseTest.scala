

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._
import mada.sequence.{Vector, vector}

import mada.peg.Compatibles._
import mada.peg._


class IcaseTest {
    def testTrivial: Unit = {
        assertTrue(icase("AbcDeFG") matches "ABcDefg")
        assertTrue(icase("AbcDeFG") matches "abCdEfG")
    }
}
