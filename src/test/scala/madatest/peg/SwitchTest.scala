

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.compatibles._
import mada.Peg.compatibles._
import mada.Peg._


class SwitchTest {
    def testTrivial: Unit = {
        val g = switch("z" inCase "e", "g" inCase "ef", "wy" inCase "wx", "xyz" inCase "w")
        assertTrue("abc" ~ g matches "abcez")
        assertTrue("abc" ~ g  matches "abcefg")
        assertTrue("abc" ~ g  matches "abcwxwy")
        assertFalse("abc" ~ g matches "abcwxyz") // false cuz longest match
    }

    def testTrivial2: Unit = {
        val g = switch(mada.Vector.stringVector("e") -> stringPeg("z"), mada.Vector.stringVector("ef") -> stringPeg("g"))
        assertTrue("abc" ~ g  matches "abcez")
    }
}
