

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import mada.peg._
import junit.framework.Assert._
import mada.peg.compatibles._


class PrettyPrinterTest {
    val expr, term, factor, digit = new Rule[Char]
    val out
//         = prettyprinter.xml
         = prettyprinter.trash

    expr    ::= out("expr")   { term >> (( '+' >> term | '-' >> term )*) }
    term    ::= out("term")   { factor >> ( '*' >> factor | '/' >> factor ).* }
    factor  ::= out("factor") { (digit+) | '(' >> expr >> ')' | '-' >> factor | '+' >> factor }
    digit   ::= range('0', '9')

    def testTrivial: Unit = {
        assertTrue(expr matches "1+2")
    }
}
