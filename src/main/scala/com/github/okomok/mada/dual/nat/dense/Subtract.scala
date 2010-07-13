

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class Subtract[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = Match(xs, ys, always0(Nil), Throw(), always0(xs), ConsMatch(xs, ys, XX(), TF(), FT(), XX())).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = Match[xs, ys, always0[Nil], Throw, always0[xs], ConsMatch[xs, ys, XX, TF, FT, XX]]#apply#asInstanceOfNatDense

    final case class Throw() extends Function0 {
        override  val self = this
        override type self = Throw
        override  def apply: apply = `throw`(new scala.UnsupportedOperationException("dual.nat.Nil.subtract positive"))
        override type apply = `throw`[scala.UnsupportedOperationException]
    }

    final case class XX() extends Function0 {
        override  val self = this
        override type self = XX
        override  def apply: apply = ConsFalse(xs.tail - ys.tail).apply.asInstanceOf[apply]
        override type apply = ConsFalse[xs#tail# -[ys#tail]]#apply
    }

    final case class TF() extends Function0 {
        override  val self = this
        override type self = TF
        override  def apply: apply = Cons(`true`, (xs.tail - ys.tail))
        override type apply = Cons[`true`, xs#tail# -[ys#tail]]
    }

    final case class FT() extends Function0 {
        override  val self = this
        override type self = FT

        override  def apply: apply = Cons(`true`, xs.tail.decrement - ys.tail).asInstanceOf[apply]
        override type apply = Cons[`true`, xs#tail#decrement# -[ys#tail]]
    }
}
