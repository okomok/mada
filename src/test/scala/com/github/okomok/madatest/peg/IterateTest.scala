

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._
import mada.sequence.{Vector, vector}

import mada.peg.Compatibles._
import mada.peg._


class IterateTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val sb = new StringBuilder
        val w = mada.sequence.vector.from("XXabcXXabcXXabcXXabc").nth

        val p = (-single('X')).+ until ~("X"|end)
        var k = 0
        for (vector.Region(v, i, j) <- p.tokenize(w)) {
            assertSame(w, v)
            sb append v(k, i).stringize
            sb append v(i, j).stringize
            k = j
        }

        assertEquals(w, mada.sequence.vector.from(sb.toString))
    }
}
