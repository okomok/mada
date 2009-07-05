

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://spirit.sourceforge.net/distrib/spirit_1_8_5/libs/spirit/doc/semantic_actions.html


package madatest.pegtest


import mada.sequence.{Vector, vector}
import mada.{Peg, peg}
import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._


class ByNameTest {
    val S = new StackActions[Char, Int]

    lazy val expr = term >>
                ( ('+' >> term){S{ (_, x, y) => x + y }} |
                  ('-' >> term){S{ (_, x, y) => x - y }} ).*
    lazy val term = factor >>
                ( ('*' >> factor){S{ (_, x, y) => x * y }} |
                  ('/' >> factor){S{ (_, x, y) => x / y }} ).*
    lazy val factor: Peg[Char] = integer |
                ('(' >> byName(expr) >> ')') |
                ('-' >> byName(factor)){S{ (_, x) => -x }} |
                ('+' >> byName(factor))
    lazy val integer = (digit.+){S{ v => v.lexical.toInt }}
    lazy val digit   = "[0-9]".r

    def testTrivial: Unit = {
        assertTrue(expr matches "12345")
        assertEquals(12345, S.pop)
        assertTrue(expr matches "1+(-1)")
        assertEquals(1+(-1), S.pop)
        assertTrue(expr matches "(1+2)*3")
        assertEquals((1+2)*3, S.pop)
        assertFalse(expr matches "(1+2)*(3*(4+5)")
        S.clear
        assertTrue(expr matches "(1+2)*(3*(4+5))")
        assertEquals((1+2)*(3*(4+5)), S.pop)
    }
}
