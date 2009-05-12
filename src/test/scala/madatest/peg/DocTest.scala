

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg
import mada.peg.compatibles._
import junit.framework.Assert._

class DocTest {
    val S, A, B = new peg.Rule[Char]

    S ::= ~(A >> !"b") >> peg.from("a").+ >> B >> !("a"|"b"|"c")
    A ::= "a" >> A.? >> "b"
    B ::= ("b" >> B.? >> "c") // { println(_) }

    def testTrivial: Unit = {
        assertTrue(S matches "abc")
        assertTrue(S matches "aabbcc")
        assertTrue(S matches "aaabbbccc")
        assertFalse(S matches "aaabbccc")
    }
}
