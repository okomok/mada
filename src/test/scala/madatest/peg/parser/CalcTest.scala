

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.Vector
import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._
import mada.peg.parser.Compatibles._


class CalcTest {

    val start = new Rule[Char]
    val digit = new Rule[Char]
    val expr = new Rule[Char]
    val term = new Rule[Char]
    val factor = new Rule[Char]

    start   := ( expr )
    digit   := ( range('0', '9') )
    expr    := ( term ~ (( '+' ~ term ^^ add | '-' ~ term ^^ sub )*) ) // Take care operator precedence.
    term    := ( factor ~ ( '*' ~ factor ^^ mul | '/' ~ factor ^^ div ).* ) // `.` is better.
    factor  := ( (digit+) ^^ int_ | '(' ~ expr ~ ')' | '-' ~ factor ^^ neg | '+' ~ factor )

    def int_(v: Vector[Char]): Unit = { }
    def add(v: Vector[Char]): Unit = { }
    def sub(v: Vector[Char]): Unit = { }
    def mul(v: Vector[Char]): Unit = { }
    def div(v: Vector[Char]): Unit = { }
    def neg(v: Vector[Char]): Unit = { }

    def testTrivial: Unit = {
        assertTrue(start.matches(Vector.stringVector("12345")))
        assertTrue(start.matches(Vector.stringVector("1+(-1)")))
        assertTrue(start.matches(Vector.stringVector("(1+2)*3")))
        assertTrue(start.matches(Vector.stringVector("(1+2)*(3*(4+5))")))
        assertFalse(start.matches(Vector.stringVector("(1+2)*(3*(4+5)")))
    }
}
