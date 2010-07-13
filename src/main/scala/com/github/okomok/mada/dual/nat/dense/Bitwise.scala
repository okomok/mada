

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class BitAnd[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = Match(xs, ys, always0(Nil), always0(Nil), always0(Nil), ConsMatch(xs, ys, new TT, new Else, new Else, new Else)).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = Match[xs, ys, always0[Nil], always0[Nil], always0[Nil], ConsMatch[xs, ys, TT, Else, Else, Else]]#apply#asInstanceOfNatDense

    class TT extends Function0 {
        override  val self = this
        override type self = TT
        override  def apply: apply = Cons(`true`, xs.tail & ys.tail)
        override type apply = Cons[`true`, xs#tail# &[ys#tail]]
    }

    class Else extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = ConsFalse(xs.tail & ys.tail).apply.asInstanceOf[apply]
        override type apply = ConsFalse[xs#tail# &[ys#tail]]#apply
    }
}


private[mada] final case class BitOr[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = Match(xs, ys, always0(Nil), always0(ys), always0(xs), ConsMatch(xs, ys, new Else, new Else, new Else, new FF)).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = Match[xs, ys, always0[Nil], always0[ys], always0[xs], ConsMatch[xs, ys, Else, Else, Else, FF]]#apply#asInstanceOfNatDense

    class FF extends Function0 {
        override  val self = this
        override type self = FF
        override  def apply: apply = Cons(`false`, xs.tail | ys.tail)
        override type apply = Cons[`false`, xs#tail# |[ys#tail]]
    }

    class Else extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = Cons(`true`, xs.tail | ys.tail)
        override type apply = Cons[`true`, xs#tail# |[ys#tail]]
    }
}
