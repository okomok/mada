

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class Equals {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, always0(`true`), always0(`false`), always0(`false`), new CC(xs, ys)).apply.asInstanceOfBoolean
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, always0[`true`], always0[`false`], always0[`false`], CC[xs, ys]]#apply#asInstanceOfBoolean

    class CC[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CC[xs, ys]
        override  def apply: apply = `if`(xs.head !== ys.head, always0(`false`), new Else(xs, ys)).apply.asInstanceOf[apply]
        override type apply = `if`[xs#head# !==[ys#head], always0[`false`], Else[xs, ys]]#apply
    }

    // for short-circuit.
    class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Else[xs, ys]
        override  def apply: apply = xs.tail === ys.tail
        override type apply = xs#tail# ===[ys#tail]
    }
}


private[mada] final class LessThan {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, always0(`false`), always0(`true`), always0(`false`), ConsMatch(xs, ys, new XXTF(xs, ys), new XXTF(xs, ys), new FT(xs, ys), new XXTF(xs, ys))).apply.asInstanceOfBoolean.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, always0[`false`], always0[`true`], always0[`false`], ConsMatch[xs, ys, XXTF[xs, ys], XXTF[xs, ys], FT[xs, ys], XXTF[xs, ys]]]#apply#asInstanceOfBoolean

    class XXTF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = XXTF[xs, ys]
        override  def apply: apply = xs.tail < ys.tail
        override type apply = xs#tail# <[ys#tail]
    }

    class FT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = FT[xs, ys]
        override  def apply: apply = (ys.tail < xs.tail).not.asInstanceOf[apply]
        override type apply = ys#tail# <[xs#tail]#not
    }
}
