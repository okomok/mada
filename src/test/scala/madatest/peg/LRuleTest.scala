

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: "Packrat Parsers Can Support Left Recursion"


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._


class LRuleTest {
    def testTrivial: Unit = {
        val v = madaVector("1-2-3")
        val expr = new mada.peg.LRule(v)
        val num = new mada.peg.LRule(v)
        val num_ = range('0','9')

        num  ::= range('0', '9')
        expr ::= ( expr >> "-" >> num | num )
        assertEquals(5L, expr parse v)
    }

    def testIndirect: Unit = {
        val v = madaVector("1-2-3")
        val expr = new mada.peg.LRule(v)
        val num = new mada.peg.LRule(v)
        val x = new mada.peg.LRule(v)

        num  ::= range('0', '9')
        x    ::= expr
        expr ::= ( x >> "-" >> num | num )
        assertEquals(5L, expr parse v)
    }
}
