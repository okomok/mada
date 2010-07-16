

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class BitAnd {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, const0(Nil), const0(Nil), const0(Nil),
            ConsMatch(xs, ys, CaseTT(xs, ys), Else(xs, ys), Else(xs, ys), Else(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, const0[Nil], const0[Nil], const0[Nil],
            ConsMatch[xs, ys, CaseTT[xs, ys], Else[xs, ys], Else[xs, ys], Else[xs, ys]]]#apply#asInstanceOfNatDense

    case class CaseTT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseTT[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail & ys.tail)
        override type apply = Cons[`true`, xs#tail# &[ys#tail]]
    }

    case class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Else[xs, ys]
        override  def apply: apply = new ConsFalse().apply(xs.tail & ys.tail).asInstanceOf[apply]
        override type apply = ConsFalse#apply[xs#tail# &[ys#tail]]
    }
}


private[mada] final class BitOr {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, const0(Nil), const0(ys), const0(xs),
            ConsMatch(xs, ys, Else(xs, ys), Else(xs, ys), Else(xs, ys), CaseFF(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, const0[Nil], const0[ys], const0[xs],
            ConsMatch[xs, ys, Else[xs, ys], Else[xs, ys], Else[xs, ys], CaseFF[xs, ys]]]#apply#asInstanceOfNatDense

    case class CaseFF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseFF[xs, ys]
        override  def apply: apply = Cons(`false`, xs.tail | ys.tail)
        override type apply = Cons[`false`, xs#tail# |[ys#tail]]
    }

    case class Else[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Else[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail | ys.tail)
        override type apply = Cons[`true`, xs#tail# |[ys#tail]]
    }
}
