

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._



class FilterFromTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.sequence.vector.from("XabcdXXabcdXX")
        val it = pe.filterFrom(v)
        assertEquals(mada.sequence.iterative.from("abcdabcd"), it)
        assertEquals(mada.sequence.iterative.from("abcdabcd"), it)
    }
}
