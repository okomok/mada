

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[seq] final class EquivTo {
     def apply[xs <: Seq, ys <: Seq, e <: Equiv](xs: xs, ys: ys, e: e): apply[xs, ys, e] =
        `if`(xs.isEmpty  && ys.isEmpty,  Const0(`true`), `if`(xs.isEmpty  !== ys.isEmpty,  Const0(`false`), Else(xs, ys, e))).apply.asInstanceOfBoolean.asInstanceOf[apply[xs, ys, e]]
    type apply[xs <: Seq, ys <: Seq, e <: Equiv] =
        `if`[xs#isEmpty# &&[ys#isEmpty], Const0[`true`], `if`[xs#isEmpty# !==[ys#isEmpty], Const0[`false`], Else[xs, ys, e]]]#apply#asInstanceOfBoolean

    case class Else[xs <: Seq, ys <: Seq, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
        type self = Else[xs, ys, e]
        override  def apply: apply = `if`(e.equiv(xs.head, ys.head), ElseThen(xs, ys, e), Const0(`false`)).apply.asInstanceOf[apply]
        override type apply        = `if`[e#equiv[xs#head, ys#head], ElseThen[xs, ys, e], Const0[`false`]]#apply
    }

    case class ElseThen[xs <: Seq, ys <: Seq, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
        type self = ElseThen[xs, ys, e]
        override  def apply: apply = xs.tail.equivTo(ys.tail, e)
        override type apply        = xs#tail#equivTo[ys#tail, e]
    }
}
