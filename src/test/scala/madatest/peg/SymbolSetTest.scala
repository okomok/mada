

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._


class SymbolSetTest {
    def testTrivial: Unit = {
        val i = ("abc" >> SymbolSet("to", "too", "tot", "tab", "so")).parse("abcto")
        assertEquals(5L, i)
    }

    def testLongestMatch: Unit = {
        assertTrue(("abc" >> SymbolSet("to", "too", "tot", "tab", "so")) matches "abctoo")
    }

    def testMethods: Unit = {
        val set = SymbolSet("to", "too", "tot", "tab", "so")
        assertFalse(set.add("to"))
        assertTrue(set.remove("too"))
    }
}