

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

    val u = new StackActions[Char, Int]
    import java.lang.Integer.parseInt

    expr    ::= term >>
                ( ('+' >> term){ u{ (_, x, y) => x + y } } |
                  ('-' >> term){ u{ (_, x, y) => x - y } } ).*
    term    ::= factor >>
                ( ('*' >> factor){ u{ (_, x, y) => x * y } } |
                  ('/' >> factor){ u{ (_, x, y) => x / y } } ).*
    factor  ::= integer |
                ('(' >> expr >> ')') |
                ('-' >> factor){ u{ (_, x) => -x } } |
                ('+' >> factor)
    integer ::= (digit.+){ u{ v => parseInt(Vector.stringize(v)) } }
    digit   ::= regex("[0-9]")

    def testTrivial: Unit = {
        assertTrue(expr matches "12345")
        assertEquals(12345, u.stack.pop)
        assertTrue(expr matches "1+(-1)")
        assertEquals(1+(-1), u.stack.pop)
        assertTrue(expr matches "(1+2)*3")
        assertEquals((1+2)*3, u.stack.pop)
        assertFalse(expr matches "(1+2)*(3*(4+5)")
        u.stack.clear
        assertTrue(expr matches "(1+2)*(3*(4+5))")
        assertEquals((1+2)*(3*(4+5)), u.stack.pop)
    }
}
