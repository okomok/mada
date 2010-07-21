

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._
import mada.sequence.{Vector, vector}
import mada.peg.Compatibles._
import mada.{Peg, peg}


class RegionActionsTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val R = new peg.RegionActions[Char]
        var w: Vector[Char] = null
        val g = R.startAt >> peg.symbolMap( peg.entry("e", "z"), peg.entry("ef", R{v => w = v} >> "g"), peg.entry("wx", "wy"), peg.entry("wxyz", R{v => w = v} >> "123") )
        assertTrue("abc" >> g >> "LL"  matches "abcefgLL")
        assertEquals(vector.from("ef"), w)
        assertTrue("abc" >> g >> "LL"  matches "abcwxyz123LL")
        assertEquals(vector.from("wxyz"), w)
    }
}
