

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class EquivTo[xs <: List, ys <: List, e <: Equiv](xs: xs, ys: ys, e: e) extends Function0 {
    type self = EquivTo[xs, ys, e]

    override  def apply: apply =
        `if`(xs.isEmpty  && ys.isEmpty,  const0(`true`), `if`(xs.isEmpty  !== ys.isEmpty,  const0(`false`), new Else)).apply.asInstanceOfBoolean.asInstanceOf[apply]
    override type apply =
        `if`[xs#isEmpty# &&[ys#isEmpty], const0[`true`], `if`[xs#isEmpty# !==[ys#isEmpty], const0[`false`],     Else]]#apply#asInstanceOfBoolean

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(e.equiv(xs.head, ys.head), new ElseThen, const0(`false`)).apply.asInstanceOf[apply]
        override type apply        = `if`[e#equiv[xs#head, ys#head],     ElseThen, const0[`false`]]#apply
    }

    class ElseThen extends Function0 {
        type self = ElseThen
        override  def apply: apply = new EquivTo(xs.tail, ys.tail, e).apply.asInstanceOf[apply]
        override type apply        =     EquivTo[xs#tail, ys#tail, e]#apply
    }
}
