

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class Equals[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = Match(xs, ys, always0(`true`), always0(`false`), always0(`false`), new CC).apply.asInstanceOfBoolean
    type apply = Match[xs, ys, always0[`true`], always0[`false`], always0[`false`], CC]#apply#asInstanceOfBoolean

    class CC extends Function0 {
        override  val self = this
        override type self = CC
        override  def apply: apply = `if`(xs.head !== ys.head, always0(`false`), new Else).apply.asInstanceOf[apply]
        override type apply = `if`[xs#head# !==[ys#head], always0[`false`], Else]#apply
    }

    // for short-circuit.
    class Else extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = xs.tail === ys.tail
        override type apply = xs#tail# ===[ys#tail]
    }
}


private[mada] final case class LessThan[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = Match(xs, ys, always0(`false`), always0(`true`), always0(`false`), ConsMatch(xs, ys, new XXTF, new XXTF, new FT, new XXTF)).apply.asInstanceOfBoolean.asInstanceOf[apply]
    type apply = Match[xs, ys, always0[`false`], always0[`true`], always0[`false`], ConsMatch[xs, ys, XXTF, XXTF, FT, XXTF]]#apply#asInstanceOfBoolean

    class XXTF extends Function0 {
        override  val self = this
        override type self = XXTF
        override  def apply: apply = xs.tail < ys.tail
        override type apply = xs#tail# <[ys#tail]
    }

    class FT extends Function0 {
        override  val self = this
        override type self = FT
        override  def apply: apply = (ys.tail < xs.tail).not.asInstanceOf[apply]
        override type apply = ys#tail# <[xs#tail]#not
    }
}
