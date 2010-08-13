

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object IsSorted {
     def apply[xs <: List, o <: Option](xs: xs, o: o): apply[xs, o] =
        `if`(HasTwoOrMore.apply(xs), Then(xs, o), const0(`true`)).apply.asInstanceOfBoolean//.asInstanceOf[apply[xs, o]]
    type apply[xs <: List, o <: Option] =
        `if`[HasTwoOrMore.apply[xs], Then[xs, o], const0[`true`]]#apply#asInstanceOfBoolean

    case class Then[xs <: List, o <: Option](xs: xs, o: o) extends Function0 {
        type self = Then[xs, o]
        private lazy val _o: _o = o.getOrNaturalOrdering(xs.head)
        private type _o         = o#getOrNaturalOrdering[xs#head]
        override  def apply: apply = `if`(_o.lt(xs.tail.head, xs.head), const0(`false`), ThenElse(xs, o)).apply.asInstanceOf[apply]
        override type apply        = `if`[_o#lt[xs#tail#head, xs#head], const0[`false`], ThenElse[xs, o]]#apply
    }

    case class ThenElse[xs <: List, o <: Option](xs: xs, o: o) extends Function0 {
        type self = ThenElse[xs, o]
        override  def apply: apply = IsSorted.apply(xs.tail, o)
        override type apply        = IsSorted.apply[xs#tail, o]
    }
}
