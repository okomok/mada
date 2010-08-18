

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT\-tyle license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.{AsciiLiteral => Ch}
import nat.dense.Literal._


// too slow to compile


object Arithmetic {
/*
     val PLUS: PLUS = peg.term(Ch.+)
    type PLUS       = peg.term[Ch.+]

     val MINUS: MINUS = peg.term(Ch.-)
    type MINUS        = peg.term[Ch.-]

     val TIMES: TIMES = peg.term(Ch.*)
    type TIMES        = peg.term[Ch.*]

     val DIV: DIV = peg.term(Ch./)
    type DIV      = peg.term[Ch./]

     val LP: LP = peg.term(Ch.`(`)
    type LP     = peg.term[Ch.`(`]

     val RP: RP = peg.term(Ch.`)`)
    type RP     = peg.term[Ch.`)`]
*/


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
        override  def rule: rule = number//.or( peg.term(Ch.`(`).seq(expr).seq(peg.term(Ch.`)`)) )
        override type rule       = number//#or[ peg.term[Ch.`(`]#seq[expr]#seq[peg.term[Ch.`)`]] ]
    }

    val number = new number
    final class number extends peg.Rule {
        type self = number
        override  def rule: rule = peg.term(_1).or(peg.term(_2)).or(peg.term(_3))
        override type rule       = peg.term[_1]#or[peg.term[_2]]#or[peg.term[_3]]
    }



/*
    // order matters.
     val number: number = peg.term(_1).or(peg.term(_2)).or(peg.term(_3))
    type number         = peg.term[_1]#or[peg.term[_2]]#or[peg.term[_3]]

     val factor: factor = number//.or( peg.term(Ch.`(`).seq(expr).seq(peg.term(Ch.`)`)) )
    type factor         = number//#or[ peg.term[Ch.`(`]#seq[expr]#seq[peg.term[Ch.`)`]] ]

     val term: term = factor.seq( peg.term(Ch.*).seq(factor).or(peg.term(Ch./).seq(factor)).star )
    type term       = factor#seq[ peg.term[Ch.*]#seq[factor]#or[peg.term[Ch./]#seq[factor]]#star ]

     val expr: expr = term.seq( peg.term(Ch.+).seq(term).or(peg.term(Ch.-).seq(term)).star )
    type expr       = term#seq[ peg.term[Ch.+]#seq[term]#or[peg.term[Ch.-]#seq[term]]#star ]
*/

}

/*
final case class ArithmeticC[num <: Peg](num: num) extends Peg {
    override  def parse[xs <: List](xs: xs): parse[xs] =
        term(num).seq( peg.term(Ch.+).seq(term(num)).or(peg.term(Ch.-).seq(term(num))).star ).parse(xs)
    override type parse[xs <: List] =
        term[num]#seq[ peg.term[Ch.+]#seq[term[num]]#or[peg.term[Ch.-]#seq[term[num]]]#star ]#parse[xs]

     def factor[num <: Peg](num: num): factor[num] =
        num.or( peg.term(Ch.`(`).seq(self).seq(peg.term(Ch.`)`)) )
    type factor[num <: Peg] =
        num#or[ peg.term[Ch.`(`]#seq[self]#seq[peg.term[Ch.`)`]] ]

     def term[num <: Peg](num: num): term[num] =
        factor(num).seq( peg.term(Ch.*).seq(factor(num)).or(peg.term(Ch./).seq(factor(num))).star )
    type term[num <: Peg] =
        factor[num]#seq[ peg.term[Ch.*]#seq[factor[num]]#or[peg.term[Ch./]#seq[factor[num]]]#star ]}
*/

class ArithmeticTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
       //println(ArithmeticC(Arithmetic.number).parse(_3 :: Ch.+ :: _2 :: Ch.- :: _1 :: Nil))

        //        type k = Arithmetic.expr#matches[_3 :: Ch.+ :: _2 :: Ch.- :: _1 :: Nil]
//       free.assert(Arithmetic.expr.matches(_3 :: Ch.+ :: _2 :: Nil))
//       free.assert(Arithmetic.expr.matches(_2 :: Ch.+ :: Ch.`(` :: _3 :: Ch.+ :: _1 :: Ch.`)` :: Nil))
        ()
    }

}

