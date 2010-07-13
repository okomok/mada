

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class BitAnd {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] = Match(xs, ys, always0(Nil), always0(Nil), always0(Nil), ConsMatch(xs, ys, new TT(xs, ys), new Else(xs, ys), new Else(xs, ys), new Else(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] = Match[xs, ys, always0[Nil], always0[Nil], always0[Nil], ConsMatch[xs, ys, TT[xs, ys], Else[xs, ys], Else[xs, ys], Else[xs, ys]]]#apply#asInstanceOfNatDense

    class TT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = TT[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail & ys.tail)
        override type apply = Cons[`true`, xs#tail# &[ys#tail]]
    }

    class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Else[xs, ys]
        override  def apply: apply = new ConsFalse().apply(xs.tail & ys.tail).asInstanceOf[apply]
        override type apply = ConsFalse#apply[xs#tail# &[ys#tail]]
    }
}


private[mada] final class BitOr {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] = Match(xs, ys, always0(Nil), always0(ys), always0(xs), ConsMatch(xs, ys, new Else(xs, ys), new Else(xs, ys), new Else(xs, ys), new FF(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] = Match[xs, ys, always0[Nil], always0[ys], always0[xs], ConsMatch[xs, ys, Else[xs, ys], Else[xs, ys], Else[xs, ys], FF[xs, ys]]]#apply#asInstanceOfNatDense

    class FF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = FF[xs, ys]
        override  def apply: apply = Cons(`false`, xs.tail | ys.tail)
        override type apply = Cons[`false`, xs#tail# |[ys#tail]]
    }

    class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Else[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail | ys.tail)
        override type apply = Cons[`true`, xs#tail# |[ys#tail]]
    }
}
