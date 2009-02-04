

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._

import mada.Pegs.Compatibles._
import mada.Pegs._


class CapturingGroupsTest {
    def testTrivial: Unit = {
        val c = new CapturingGroups[String, Char]
        val p = ("abcd" >> c("name", "EFG") >> "hi" >> c("name"))
        assertTrue(p matches "abcdEFGhiEFG")
    }
}
