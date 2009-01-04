

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.peg._
import junit.framework.Assert._
import mada.vec.Compatibles._
import mada.StringCompatibles._


class SymbolsTest {
    def testTrivial: Unit = {
        val map = new TSTreeMap[Char, Int](mada.vec.stl.Less[Char])
        map.put("pineapple", 10)
        map.put("orange", 11)
        map.put("banana", 12)
        map.put("applepie", 13)
        map.put("apple", 14)

        assertEquals(10, map.get("pineapple").get)
        assertEquals(11, map.get("orange").get)
        assertEquals(12, map.get("banana").get)
        assertEquals(13, map.get("applepie").get)
        assertEquals(14, map.get("apple").get)
    }
}
