

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package pegtest


// See: http://www.javaworld.com/javaworld/jw-02-2001/jw-0216-ternary.html


import junit.framework.Assert._

import mada.peg.Compatibles._
import mada.peg._
import mada.sequence.vector



class TSTreeTest {
    def testTSTree: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.compare.fromGetOrdered[Char])

        assertFalse(tree.containsKey("to"))
        assertFalse(tree.containsKey(""))

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
        assertFalse(tree.containsKey(""))

        assertEquals(None, tree.parse(vector.from("ztot").nth, 0, 4))
        assertEquals(None, tree.parse(vector.from("t").nth, 0, 1))
        assertEquals(None, tree.parse(vector.from("tzzzzz").nth, 0, 6))
        assertEquals(None, tree.parse(vector.from("").nth, 0, 0))
        assertEquals(3, tree.parse(vector.from("tot").nth, 0, 3).get._2)
        assertEquals(3, tree.parse(vector.from("totzzzzz").nth, 0, 8).get._2)
        assertEquals(2, tree.parse(vector.from("toazzzzz").nth, 0, 8).get._2)

        /* empty-string key
        tree.put("", "EMPTY")
        assertTrue(tree.containsKey(""))
        assertEquals(0, tree.parse("ztot", 0, 4).get._2) // 0-length match
        assertEquals(2, tree.parse("zzzzztot", 2, 8).get._2) // 0-length match
        */
    }

    def testBound: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.compare.fromGetOrdered[Char])

        tree.put("t", "t")
        assertFalse(tree.containsKey(""))
        assertTrue(tree.containsKey("t"))
        assertFalse(tree.containsKey(""))
    }

    /*
    def testEmptyStringKey: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.compare.fromGetOrdered[Char])

        tree.put("", "EMPTY")
        assertTrue(tree.containsKey(""))
        assertEquals("EMPTY", tree.get("").get)
    }
    */

    def testRemove: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.compare.fromGetOrdered[Char])

        assertEquals(0, tree.size)
        assertTrue(tree.isEmpty)
        tree.toString
        assertFalse(tree.containsKey(""))
        assertFalse(tree.containsKey("zzz"))

        tree.put("to", "to")
        tree.put("too", "too")
        tree.put("tot", "tot")
        assertEquals(3, tree.size)
        assertFalse(tree.isEmpty)
        tree.put("tab", "tab")
        tree.put("so", "so")
        assertEquals(5, tree.size)
        assertFalse(tree.isEmpty)

        assertEquals(tree.toString, tree.clone.toString)

        assertFalse(tree.remove("to").isEmpty)
        assertFalse(tree.containsKey("to"))
        //println(tree.toString)

        // remove leaf and its parent
        assertFalse(tree.remove("tab").isEmpty)
        assertFalse(tree.isEmpty)
        //println(tree.toString)

        // remove leaf
        assertFalse(tree.remove("tot").isEmpty)
        assertFalse(tree.containsKey("tot"))
        assertTrue(tree.remove("tot").isEmpty)
        //println(tree.toString)

        assertFalse(tree.remove("so").isEmpty)
        assertFalse(tree.remove("too").isEmpty)
        assertTrue(tree.isEmpty)
        //println(tree.toString)

        tree.clear
        assertEquals(0, tree.size)
        assertTrue(tree.isEmpty)
        tree.put("to", "to")
        tree.put("too", "too")
        tree.put("tot", "tot")
        assertEquals(3, tree.size)
        //println(tree.toString)

        val old = "OLD"
        tree.put("old", old)
        assertSame(old, tree.put("old", "NEW").get)
    }

    def testIterator: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.compare.fromGetOrdered[Char])

        tree.put("to", "to")
        tree.put("too", "too")
        tree.put("tot", "tot")
        tree.put("tab", "tab")
        tree.put("so", "so")

        for (n <- tree.iterator) {
            assertTrue(tree.containsKey(n._2))
            //println(n)
        }
    }

    def testIteratorBound: Unit = {
        val tree = new mada.peg.TSTree[Char, String](mada.compare.fromGetOrdered[Char])
        assertFalse(tree.iterator.hasNext)

        tree.put("t", "t")

        var c = 0
        for (n <- tree.iterator) {
            assertEquals(mada.sequence.vector.from("t"), n._1)
            c += 1
        }
        assertEquals(1, c)
    }
}
