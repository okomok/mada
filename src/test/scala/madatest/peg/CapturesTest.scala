

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._


class CapturesTest {
    def testTrivial: Unit = {
        val c = new Captures[Char]
        val p = ("abcd" >> c(10, "EFG") >> "hi" >> c(10))
        assertTrue(p matches "abcdEFGhiEFG")
    }
}
