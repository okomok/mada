

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

// import mada.Compare.madaCompareFromGetOrdered


import mada.sequence.{Vector, vector}
import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._


class PrettyPrinterTest extends junit.framework.TestCase {
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
