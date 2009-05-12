

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._

import mada.Peg.Compatibles._
import mada.Peg._
import mada.vector._
import mada.vector.compatibles._


class SymbolMapTest {
    def testTrivial: Unit = {
        val g = SymbolMap("e" --> "z", "ef" --> "g", /* "" --> "DEFAULT", */ "wx" --> "wy", "w" --> "xyz")
        assertTrue("abc" >> g >> "LL"  matches "abcezLL")
        assertTrue("abc" >> g >> "LL"  matches "abcefgLL")
        assertTrue("abc" >> g >> "LL"  matches "abcwxwyLL")
        assertFalse("abc" >> g >> "LL" matches "abcwxyzLL") // false cuz longest match
//        assertTrue("abc" >> g >> "LL" matches "abcDEFAULTLL")
    }

    def testTrivial2: Unit = {
        val g = SymbolMap(mada.vector.unstringize("e") -> mada.Peg.unstringize("z"), mada.vector.unstringize("ef") -> mada.Peg.unstringize("g"))
        assertTrue("abc" >> g  matches "abcez")
    }

    def testIterator: Unit = {
        val g = SymbolMap("e" --> "z", "ef" --> "g", "wx" --> "wy", "w" --> "xyz")
        for (n <- g.elements) {
            // println(n)
        }
    }
}
