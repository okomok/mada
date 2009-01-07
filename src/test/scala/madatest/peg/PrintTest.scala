

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Vector
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.compatibles._


class PrintTest {
    val (expr, term, factor, digit) = rule4[Char]
    val out = prettyPrinter

    expr    ::= ( term ~ (( '+' ~ term ^^ add | '-' ~ term ^^ sub )*) ).printTo(out)
    term    ::= ( factor ~ ( '*' ~ factor ^^ mul | '/' ~ factor ^^ div ).* ).printTo(out)
    factor  ::= ( (digit+) ^^ int_ | '(' ~ expr ~ ')' | '-' ~ factor ^^ neg | '+' ~ factor ).printTo(out)
    digit   ::= range('0', '9')

    def int_(v: Vector[Char]): Unit = { }
    def add(v: Vector[Char]): Unit = { }
    def sub(v: Vector[Char]): Unit = { }
    def mul(v: Vector[Char]): Unit = { }
    def div(v: Vector[Char]): Unit = { }
    def neg(v: Vector[Char]): Unit = { }

    def testTrivial: Unit = {
        assertTrue(expr.matches(Vector.stringVector("1+2")))
    }
}
