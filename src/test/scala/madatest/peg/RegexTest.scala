

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._
import java.util.regex.Pattern


class RegexTest {
    def testTrivial: Unit = {
        val p = ("abc" >> Pattern.compile(".*b") >> "c")
        assertTrue(p matches "abcaaaaaaabc")
    }
}
