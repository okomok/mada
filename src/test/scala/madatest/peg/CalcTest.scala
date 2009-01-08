

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Vector
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._


class CalcTest {
    val (expr, term, factor, digit) = rule4[Char]

    expr    ::= term ~ (( '+' ~ term ^^ add | '-' ~ term ^^ sub )*) // Take care operator precedence.
    term    ::= factor ~ ( '*' ~ factor ^^ mul | '/' ~ factor ^^ div ).* // `.` is better.
    factor  ::= ( (digit+) ^^ int_ | '(' ~ expr ~ ')' | '-' ~ factor ^^ neg | '+' ~ factor )
    digit   ::= range('0', '9')

    def int_(v: Vector[Char]): Unit = { }
    def add(v: Vector[Char]): Unit = { }
    def sub(v: Vector[Char]): Unit = { }
    def mul(v: Vector[Char]): Unit = { }
    def div(v: Vector[Char]): Unit = { }
    def neg(v: Vector[Char]): Unit = { }

    def testTrivial: Unit = {
        assertTrue(expr.matches(Vector.stringVector("12345")))
        assertTrue(expr.matches(Vector.stringVector("1+(-1)")))
        assertTrue(expr.matches(Vector.stringVector("(1+2)*3")))
        assertTrue(expr.matches(Vector.stringVector("(1+2)*(3*(4+5))")))
        assertFalse(expr.matches(Vector.stringVector("(1+2)*(3*(4+5)")))
    }
}
