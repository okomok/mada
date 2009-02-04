

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._

import mada.Pegs.Compatibles._
import mada.Pegs._
import java.util.regex.Pattern


class RegexTest {
    def testTrivial: Unit = {
        val p = ("abc" >> Pattern.compile(".*b") >> "c")
        assertTrue(p matches "abcaaaaaaabc")
    }

    def testTrivial2: Unit = {
        val p = ("abc" >> Pattern.compile("ef") >> "c")
        assertFalse(p matches "abcDefc")
    }

    def testTransparent: Unit = {
        val p = ("abc" >> Pattern.compile("(?<=c)d(?=E)") >> "E")
        assertTrue(p matches "abcdE")
    }
}
