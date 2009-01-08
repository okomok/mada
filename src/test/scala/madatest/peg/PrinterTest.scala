

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Vector
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._


class PrinterTest {
    val (expr, term, factor, digit) = rule4[Char]
    val out
        // = new Printer
         = { (x: String, y: mada.Peg[Char]) => y }

    expr    ::= out( "expr", term ~ (( '+' ~ term | '-' ~ term )*) )
    term    ::= out( "term", factor ~ ( '*' ~ factor | '/' ~ factor ).* )
    factor  ::= out( "factor", (digit+) | '(' ~ expr ~ ')' | '-' ~ factor | '+' ~ factor )
    digit   ::= range('0', '9')

    def testTrivial: Unit = {
        assertTrue(expr.matches(Vector.stringVector("1+2")))
    }
}
