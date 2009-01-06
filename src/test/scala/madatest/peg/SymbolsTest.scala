

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


import junit.framework.Assert._
import mada.Vector.compatibles._
import mada.Peg.compatibles._
import mada.Peg._


class symbolsTest {
    def testTrivial: Unit = {
        val i = ("abc" ~ symbols("to", "too", "tot", "tab", "so")).parse("abcto")
        assertEquals(5L, i)
    }

    def testLongestMatch: Unit = {
        assertTrue(("abc" ~ symbols("to", "too", "tot", "tab", "so")) matches "abctoo")
    }

    def testTSTree: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.vec.stl.Less[Char])

        assertFalse(tree.contains("to"))
        assertFalse(tree.contains(""))

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
        assertFalse(tree.contains(""))

        assertEquals(FAILED, tree.parse("ztot"))
        assertEquals(FAILED, tree.parse("t"))
        assertEquals(FAILED, tree.parse("tzzzzz"))
        assertEquals(FAILED, tree.parse(""))
        assertEquals(3L, tree.parse("tot"))
        assertEquals(3L, tree.parse("totzzzzz"))
    }

    def testBound: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.vec.stl.Less[Char])

        tree.put("t", "t")
        assertFalse(tree.contains(""))
        assertTrue(tree.contains("t"))
    }
}
