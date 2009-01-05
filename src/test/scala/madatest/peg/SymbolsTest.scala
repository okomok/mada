

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


import mada.peg._
import junit.framework.Assert._
import mada.vec.Compatibles._
import mada.peg.Compatibles._


class SymbolsTest {
    def testTrivial: Unit = {
        val i = ("abc" ~ Symbols("to", "too", "tot", "tab", "so")).parse("abcto")
        assertEquals(5L, i)
    }

    def testLongestMatch: Unit = {
        assertTrue(("abc" ~ Symbols("to", "too", "tot", "tab", "so")) matches "abctoo")
    }

    def testTSTree: Unit = {
        val tree = new mada.peg.detail.TSTree[Char, String](mada.vec.stl.Less[Char])
        tree.put("to", "to")
        //println(tree.toString)
        tree.put("too", "too")
        //println(tree.toString)
        tree.put("tot", "tot")
        //println(tree.toString)
        tree.put("tab", "tab")
        //println(tree.toString)
        tree.put("so", "so")
        //println(tree.toString)

        assertEquals("so", tree.get("so").get)
        assertEquals("tab", tree.get("tab").get)
        assertEquals("to", tree.get("to").get)
        assertEquals("too", tree.get("too").get)
        assertEquals("tot", tree.get("tot").get)
        assertFalse(tree.contains("")) // works.

        assertEquals(Parser.FAILED, tree.parse("ztot"))
        assertEquals(Parser.FAILED, tree.parse("t"))
        assertEquals(Parser.FAILED, tree.parse("tzzzzz"))
        assertEquals(Parser.FAILED, tree.parse(""))
        assertEquals(3L, tree.parse("tot"))
        assertEquals(3L, tree.parse("totzzzzz"))
    }
}
