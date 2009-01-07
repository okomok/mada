

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://spirit.sourceforge.net/distrib/spirit_1_8_5/libs/spirit/doc/stored_rule.html


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.compatibles._
import mada.Vector.compatibles._


class RuleTest {
    val (start) = rule1[Char]

    // left-recursion using copy
    start ::= "a"
    start ::= (start.copy | "b")
    start ::= (start.copy | "c")
    start ::= start.copy.*

    def testTrivial: Unit = {
        assertTrue(start matches "abcaabbcabc")
        assertFalse(start matches "abcXaabbcabc")
    }
}
