

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://spirit.sourceforge.net/distrib/spirit_1_8_5/libs/spirit/doc/semantic_actions.html


package madatest.peg


import mada.Vector
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._


class StackActionsTest {
    val expr, term, factor, integer, digit = new Rule[Char]

    val S = new StackActions[Char, Int]

    expr    ::= term >>
                ( ('+' >> term){S{ (_, x, y) => x + y }} |
                  ('-' >> term){S{ (_, x, y) => x - y }} ).*
    term    ::= factor >>
                ( ('*' >> factor){S{ (_, x, y) => x * y }} |
                  ('/' >> factor){S{ (_, x, y) => x / y }} ).*
    factor  ::= integer |
                ('(' >> expr >> ')') |
                ('-' >> factor){S{ (_, x) => -x }} |
                ('+' >> factor)
    integer ::= (digit.+){S{ v => Vector.stringize(v).toInt }}
    digit   ::= regex("[0-9]")

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
