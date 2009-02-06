

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._

import mada.Peg.Compatibles._
import mada.Peg._
import mada.Vector.Compatibles._


class SymbolSetTest {
    def testTrivial: Unit = {
        val i = ("abc" >> SymbolSet("to", "too", "tot", "tab", "so")).lookingAt("abcto".nth).get
        assertEquals(5, i)
    }

    def testLongestMatch: Unit = {
        assertTrue(("abc" >> SymbolSet("to", "too", "tot", "tab", "so")) matches "abctoo")
    }

    def testMethods: Unit = {
        val set = SymbolSet("to", "too", "tot", "tab", "so")
        //assertFalse(set.+=("to"))
        //assertTrue(set.-=("too"))
        ()
    }
}
