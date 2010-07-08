

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] final case class Subtract[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, Always0(NatNil), Throw(), Always0(xs), xs.matchCaseCons(ys, XX(), TF(), FT(), XX())).apply.asInstanceOfNat.asInstanceOf[apply]
    type apply = xs#matchCaseNil[ys, Always0[NatNil], Throw, Always0[xs], xs#matchCaseCons[ys, XX, TF, FT, XX]]#apply#asInstanceOfNat

    final case class Throw() extends Function0 {
        override  def self = this
        override type self = Throw
        override  def apply: apply = `throw`(new scala.UnsupportedOperationException("dual.NatNil.subtract positive"))
        override type apply = `throw`[scala.UnsupportedOperationException]
    }

    final case class XX() extends Function0 {
        override  def self = this
        override type self = XX
        override  def apply: apply = ConsFalse(xs.tail - ys.tail).apply.asInstanceOf[apply]
        override type apply = ConsFalse[xs#tail# -[ys#tail]]#apply
    }

    final case class ConsFalse[zs <: Nat](zs: zs) extends Function0 {
        override  def self = this
        override type self = ConsFalse[zs]
        override  def apply: apply = zs.isEmpty.`if`(Always0(zs), Always0(NatCons(`false`, zs))).apply
        override type apply = zs#isEmpty#`if`[Always0[zs], Always0[NatCons[`false`, zs]]]#apply
    }

    final case class TF() extends Function0 {
        override  def self = this
        override type self = TF
        override  def apply: apply = NatCons(`true`, (xs.tail - ys.tail))
        override type apply = NatCons[`true`, xs#tail# -[ys#tail]]
    }

    final case class FT() extends Function0 {
        override  def self = this
        override type self = FT

        override  def apply: apply = NatCons(`true`, xs.tail.decrement - ys.tail).asInstanceOf[apply]
        override type apply = NatCons[`true`, xs#tail#decrement# -[ys#tail]]
    }
}
