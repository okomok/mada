

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Vectors
import mada.Pegs._
import junit.framework.Assert._
import mada.Pegs.Compatibles._


class PrettyPrinterTest {
    val expr, term, factor, digit = new Rule[Char]
    val out
//         = PrettyPrinter.xml
         = PrettyPrinter.trash

    expr    ::= out( "expr", term >> (( '+' >> term | '-' >> term )*) )
    term    ::= out( "term", factor >> ( '*' >> factor | '/' >> factor ).* )
    factor  ::= out( "factor", (digit+) | '(' >> expr >> ')' | '-' >> factor | '+' >> factor )
    digit   ::= range('0', '9')

    def testTrivial: Unit = {
        assertTrue(expr.matches(Vectors.fromString("1+2")))
    }
}
