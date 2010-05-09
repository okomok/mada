

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.{Peg, peg}
import junit.framework.Assert._


class SeqOptTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val p = "abc" >?>: "de" >?>: "fghi" >?>: peg.from("jk")
        assertTrue(p matches "abc")
        assertTrue(p matches "abcde")
        assertTrue(p matches "abcdefghi")
        assertTrue(p matches "abcdefghijk")

        assertTrue(p >> "d" matches "abcd")
        assertTrue(p >> "fg" matches "abcdefg")
        assertTrue(p >> "j" matches "abcdefghij")
        assertTrue(p >> "d" matches "abcd")

        assertFalse(p matches "ab")
        assertFalse(p matches "abcfghi")
        assertFalse(p matches "abjk")
        assertFalse(p matches "")
    }
}
