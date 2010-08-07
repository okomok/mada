

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object EquivTo {
     def apply[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e): apply[xs, ys, e] =
        `if`(xs.isEmpty.and(ys.isEmpty), const0(`true`), `if`(xs.isEmpty.nequal(ys.isEmpty), const0(`false`), Else(xs, ys, e))).apply.asInstanceOfBoolean.asInstanceOf[apply[xs, ys, e]]
    type apply[xs <: List, ys <: List, e <: Equiv] =
        `if`[xs#isEmpty#and[ys#isEmpty], const0[`true`], `if`[xs#isEmpty#nequal[ys#isEmpty], const0[`false`], Else[xs, ys, e]]]#apply#asInstanceOfBoolean

    case class Else[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
        type self = Else[xs, ys, e]
        override  def apply: apply = `if`(e.equiv(xs.head, ys.head), ElseThen(xs, ys, e), const0(`false`)).apply.asInstanceOf[apply]
        override type apply        = `if`[e#equiv[xs#head, ys#head], ElseThen[xs, ys, e], const0[`false`]]#apply
    }

    case class ElseThen[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
        type self = ElseThen[xs, ys, e]
        override  def apply: apply = EquivTo.apply(xs.tail, ys.tail, e).asInstanceOf[apply]
        override type apply        = EquivTo.apply[xs#tail, ys#tail, e]
    }
}
