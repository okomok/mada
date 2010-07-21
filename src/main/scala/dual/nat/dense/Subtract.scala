

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class Subtract {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys, const0(Nil), Throw(xs, ys), const0(xs),
            ConsMatch(xs, ys, CaseXX(xs, ys), CaseTF(xs, ys), CaseFT(xs, ys), CaseXX(xs, ys))).apply.asInstanceOfNatDense.asInstanceOf[apply[xs, ys]]
    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys, const0[Nil], Throw[xs, ys], const0[xs],
            ConsMatch[xs, ys, CaseXX[xs, ys], CaseTF[xs, ys], CaseFT[xs, ys], CaseXX[xs, ys]]]#apply#asInstanceOfNatDense

    case class Throw[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = Throw[xs, ys]
        override  def apply: apply = `throw`(new scala.UnsupportedOperationException("dual.nat.dense.Nil.subtract positive"))
        override type apply = `throw`[scala.UnsupportedOperationException]
    }

    case class CaseXX[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseXX[xs, ys]
        override  def apply: apply = new ConsFalse().apply(xs.tail - ys.tail).asInstanceOf[apply]
        override type apply = ConsFalse#apply[xs#tail# -[ys#tail]]
    }

    case class CaseTF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseTF[xs, ys]
        private   def xst_sub_yst: xst_sub_yst = (xs.tail - ys.tail).asInstanceOf[xst_sub_yst]
        private  type xst_sub_yst = xs#tail# -[ys#tail]
        override  def apply: apply = Cons(`true`, xst_sub_yst)
        override type apply = Cons[`true`, xst_sub_yst]
    }

    case class CaseFT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends Function0 {
        override  val self = this
        override type self = CaseFT[xs, ys]
        override  def apply: apply = Cons(`true`, xs.tail.decrement - ys.tail).asInstanceOf[apply]
        override type apply = Cons[`true`, xs#tail#decrement# -[ys#tail]]
    }
}
