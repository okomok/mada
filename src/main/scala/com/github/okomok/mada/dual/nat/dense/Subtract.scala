

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class Subtract {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] = Match(xs, ys, always0(Nil), new Throw(xs, ys), always0(xs), ConsMatch(xs, ys, new XX(xs, ys), new TF(xs, ys), new FT(xs, ys), new XX(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] = Match[xs, ys, always0[Nil], Throw[xs, ys], always0[xs], ConsMatch[xs, ys, XX[xs, ys], TF[xs, ys], FT[xs, ys], XX[xs, ys]]]#apply#asInstanceOfNatDense

    class Throw[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Throw[xs, ys]
        override  def apply: apply = `throw`(new scala.UnsupportedOperationException("dual.nat.Nil.subtract positive"))
        override type apply = `throw`[scala.UnsupportedOperationException]
    }

    class XX[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = XX[xs, ys]
        override  def apply: apply = new ConsFalse().apply(xs.tail - ys.tail).asInstanceOf[apply]
        override type apply = ConsFalse#apply[xs#tail# -[ys#tail]]
    }

    class TF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = TF[xs, ys]
        override  def apply: apply = Cons(`true`, (xs.tail - ys.tail))
        override type apply = Cons[`true`, xs#tail# -[ys#tail]]
    }

    class FT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = FT[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail.decrement - ys.tail).asInstanceOf[apply]
        override type apply = Cons[`true`, xs#tail#decrement# -[ys#tail]]
    }
}
