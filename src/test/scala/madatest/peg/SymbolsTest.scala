

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


import mada.peg._
import junit.framework.Assert._
import mada.vec.Compatibles._


class SymbolsTest {
    def testTrivial: Unit = {
        val map = new TSTreeMap[Char, String](mada.vec.stl.Less[Char])
        map.put("to", "to")
        println(map.toString)
        map.put("too", "too")
        println(map.toString)
        map.put("tot", "tot")
        println(map.toString)
        map.put("tab", "tab")
        println(map.toString)
        map.put("so", "so")
        println(map.toString)

        assertEquals("so", map.get("so").get)
        assertEquals("tab", map.get("tab").get)
        assertEquals("to", map.get("to").get)
        assertEquals("too", map.get("too").get)
        assertEquals("tot", map.get("tot").get)
        assertFalse(map.contains("")) // works.

        assertEquals(Parser.FAILED, map.parse("ztot"))
        assertEquals(Parser.FAILED, map.parse("t"))
        assertEquals(Parser.FAILED, map.parse("tzzzzz"))
        assertEquals(Parser.FAILED, map.parse(""))
        assertEquals(3L, map.parse("tot"))
        assertEquals(3L, map.parse("totzzzzz"))
    }
}
