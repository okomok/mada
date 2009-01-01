

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.Vector
import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._
import mada.peg.parser.Compatibles._


class CalcTest {
    def start = expr
    def digit = range('0', '9')
    def expr = term ~ ( '+' ~ term ^^ add | '-' ~ term ^^ sub )*
    def term = factor ~ ( '*' ~ factor ^^ mul | '/' ~ factor ^^ div )*
    def factor: Parser[Char] = (digit+) ^^ int_ | '(' ~ expr ~ ')' | '-' ~ factor ^^ neg | '+' ~ factor

    def int_(v: Vector[Char]): Unit = { }
    def add(v: Vector[Char]): Unit = { }
    def sub(v: Vector[Char]): Unit = { }
    def mul(v: Vector[Char]): Unit = { }
    def div(v: Vector[Char]): Unit = { }
    def neg(v: Vector[Char]): Unit = { }
}
