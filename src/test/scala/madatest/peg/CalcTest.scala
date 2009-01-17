

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Vector
import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._


class CalcTest {
    val (expr, term, factor, integer, digit) = Rule.make5[Char]

    val stack = new java.util.ArrayDeque[Int]
    import stack.{ push, pop }

    expr    ::= term >>
                ( ('+' >> term){ case _ => push(pop + pop) } |
                  ('-' >> term){ case _ => push(pop - pop) } ).*
    term    ::= factor >>
                ( ('*' >> factor){ case _ => push(pop * pop) } |
                  ('/' >> factor){ case _ => push(pop / pop) } ).*
    factor  ::= integer |
                ('(' >> expr >> ')') |
                ('-' >> factor){ case _ => push(-pop) } |
                ('+' >> factor)
    integer ::= (digit.+){ case (v,i,j) => push(parseInt(v(i,j))) }
    digit   ::= range('0','9')

    def parseInt(v: Vector[Char]): Int = java.lang.Integer.parseInt(Vector.toString(v))

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
