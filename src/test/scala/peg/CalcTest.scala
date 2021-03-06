

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


// See: http://spirit.sourceforge.net/distrib/spirit_1_8_5/libs/spirit/doc/semantic_actions.html


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._



class CalcTest extends org.scalatest.junit.JUnit3Suite {
    val expr, term, factor, integer, digit = new Rule[Char]

    val stack = new java.util.ArrayDeque[Int]
    import stack.{ push, pop }

    expr    ::= term >>
                ( ('+' >> term){ _ => push(pop + pop) } |
                  ('-' >> term){ _ => push(pop - pop) } ).*
    term    ::= factor >>
                ( ('*' >> factor){ _ => push(pop * pop) } |
                  ('/' >> factor){ _ => push(pop / pop) } ).*
    factor  ::= integer |
                ('(' >> expr >> ')') |
                ('-' >> factor){ _ => push(-pop) } |
                ('+' >> factor)
    integer ::= (digit.+){ v => push(v.stringize.toInt) }
    digit   ::= regex("[0-9]")

    def testTrivial: Unit = {
        assertTrue(expr matches "12345")
        assertEquals(12345, pop)
        assertTrue(expr matches "1+(-1)")
        assertEquals(1+(-1), pop)
        assertTrue(expr matches "(1+2)*3")
        assertEquals((1+2)*3, pop)
        assertFalse(expr matches "(1+2)*(3*(4+5)")
        stack.clear
        assertTrue(expr matches "(1+2)*(3*(4+5))")
        assertEquals((1+2)*(3*(4+5)), pop)
    }
}
