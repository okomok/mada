

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import junit.framework.Assert._

import mada.peg.Compatibles._
import mada.peg._
import mada.sequence.vector


class SymbolMapTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val g = symbolMap(entry(vector.from("e"), "z"), entry("ef", "g"), /* entry("", "DEFAULT"), */ entry("wx", "wy"), entry("w", "xyz"))
        assertTrue("abc" >> g >> "LL"  matches "abcezLL")
        assertTrue("abc" >> g >> "LL"  matches "abcefgLL")
        assertTrue("abc" >> g >> "LL"  matches "abcwxwyLL")
        assertFalse("abc" >> g >> "LL" matches "abcwxyzLL") // false cuz longest match
//        assertTrue("abc" >> g >> "LL" matches "abcDEFAULTLL")
    }

    def testTrivial2: Unit = {
        val g = symbolMap(mada.sequence.vector.unstringize("e") -> mada.peg.unstringize("z"), mada.sequence.vector.unstringize("ef") -> mada.peg.unstringize("g"))
        assertTrue("abc" >> g  matches "abcez")
    }

    def testIterator: Unit = {
        val g = symbolMap(entry("e", "z"), entry("ef", "g"), entry("wx", "wy"), entry("w", "xyz"))
        for (n <- g.iterator) {
            // println(n)
        }
    }
}
