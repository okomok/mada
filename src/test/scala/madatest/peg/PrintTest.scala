

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

    expr    ::= ( term ~ (( '+' ~ term | '-' ~ term )*) ).named("expr").printed(out)
    term    ::= ( factor ~ ( '*' ~ factor | '/' ~ factor ).* ).printed(out, "term")
    factor  ::= ( (digit+) | '(' ~ expr ~ ')' | '-' ~ factor | '+' ~ factor ).named("factor").printed(out)
    digit   ::= range('0', '9')

    def testTrivial: Unit = {
        assertTrue(expr.matches(Vector.stringVector("1+2")))
    }
}
