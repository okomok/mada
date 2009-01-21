

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: "Packrat Parsers Can Support Left Recursion"


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._


class LRuleTest {
    /*
    val v = madaVector("1-2-3")
    val lr = new mada.peg.LRules[Char]
    val expr = lr.makeRule
    val num = lr.makeRule
    val x = lr.makeRule

    def testTrivial: Unit = {
        num  ::= range('0','9')
        expr ::= ( expr >> "-" >> num | num )
        assertEquals(5, expr parse v)
    }

    def testIndirect: Unit = {
        num  ::= range('0', '9')
        x    ::= expr
        expr ::= ( x >> "-" >> num | num )
        assertEquals(5, expr parse v)
    }
    */
}
