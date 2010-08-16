

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT\-tyle license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.{AsciiLiteral => Ch}
import nat.dense.Literal._


// too slow to compile

/*
object Arithmetic {

    val expr = new expr
    final class expr extends peg.Rule {
        type self = expr
        override  def rule: rule = term.seq( peg.term(Ch.+).seq(term).or(peg.term(Ch.-).seq(term)).star )
        override type rule       = term#seq[ peg.term[Ch.+]#seq[term]#or[peg.term[Ch.-]#seq[term]]#star ]
    }

    val term = new term
    final class term extends peg.Rule {
        type self = term
        override  def rule: rule = factor.seq( peg.term(Ch.*).seq(factor).or(peg.term(Ch./).seq(factor)).star )
        override type rule       = factor#seq[ peg.term[Ch.*]#seq[factor]#or[peg.term[Ch./]#seq[factor]]#star ]
    }

    val factor = new factor
    final class factor extends peg.Rule {
        type self = factor
        override  def rule: rule = number.or( peg.term(Ch.`(`).seq(expr).seq(peg.term(Ch.`)`)) )
        override type rule       = number#or[ peg.term[Ch.`(`]#seq[expr]#seq[peg.term[Ch.`)`]] ]
    }

    val number = new number
    final class number extends peg.Rule {
        type self = number
        override  def rule: rule = peg.term(_1).or(peg.term(_2)).or(peg.term(_3))
        override type rule       = peg.term[_1]#or[peg.term[_2]]#or[peg.term[_3]]
    }

}

class ArithmeticTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
       free.assert(Arithmetic.expr.matches(_3 :: Ch.* :: _2 :: Ch.- :: _1 :: Nil))
//       free.assert(Arithmetic.expr.matches(_2 :: Ch.* :: Ch.`(` :: _3 :: Ch.+ :: _1 :: Ch.`)` :: Nil))
        ()
    }

}

*/
