

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsEquivTo {
     def apply[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e): apply[xs, ys, e] =
        `if`(ys.isEmpty  ||(xs.size  !== ys.size),  Const0(`false`), Else(xs, ys, e)).apply.asInstanceOfBoolean.asInstanceOf[apply[xs, ys, e]]
    type apply[xs <: List, ys <: List, e <: Equiv] =
        `if`[ys#isEmpty# ||[xs#size# !==[ys#size]], Const0[`false`], Else[xs, ys, e]]#apply#asInstanceOfBoolean

    case class Else[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
        type self = Else[xs, ys, e]
        override  def apply: apply = `if`(e.equiv(xs.head, ys.head), ElseThen(xs, ys, e), Const0(`false`)).apply.asInstanceOf[apply]
        override type apply        = `if`[e#equiv[xs#head, ys#head], ElseThen[xs, ys, e], Const0[`false`]]#apply
    }

    case class ElseThen[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
        type self = ElseThen[xs, ys, e]
        override  def apply: apply = xs.tail.equivTo(ys.tail, e)
        override type apply        = xs#tail#equivTo[ys#tail, e]
    }
}
