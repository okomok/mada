

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class Add {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, always0(Nil), always0(ys), always0(xs), ConsMatch(xs, ys, new TT(xs, ys), new XF(xs, ys), new FX(xs, ys), new XF(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, always0[Nil], always0[ys], always0[xs], ConsMatch[xs, ys, TT[xs, ys], XF[xs, ys], FX[xs, ys], XF[xs, ys]]]#apply#asInstanceOfNatDense

    class TT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = TT[xs, ys]
        override  def apply: apply = Cons(`false`, (xs.tail + ys.tail).increment).asInstanceOf[apply]
        override type apply = Cons[`false`, xs#tail# +[ys#tail]#increment]
    }

    class XF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = XF[xs, ys]
        override  def apply: apply = Cons(xs.head, (xs.tail + ys.tail))
        override type apply = Cons[xs#head, xs#tail# +[ys#tail]]
    }

    class FX[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = FX[xs, ys]
        override  def apply: apply = Cons(ys.head, xs.tail + ys.tail)
        override type apply = Cons[ys#head, xs#tail# +[ys#tail]]
    }
}
