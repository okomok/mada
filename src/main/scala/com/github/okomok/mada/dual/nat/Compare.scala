

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] final case class Equals[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, Always0(`true`), Always0(`false`), Always0(`false`), CC()).apply.asInstanceOfBoolean
    type apply = xs#matchCaseNil[ys, Always0[`true`], Always0[`false`], Always0[`false`], CC]#apply#asInstanceOfBoolean

    final case class CC() extends Function0 {
        override  def self = this
        override type self = CC
        override  def apply = xs.head.!==(ys.head).`if`(Always0(`false`), Else()).apply
        override type apply = xs.head# !==[ys.head]# `if`[Always0[`false`], Else]#apply
    }

    // for short-circuit.
    final case class Else() extends Function0 {
        override  def self = this
        override type self = Else
        override  def apply = xs.tail === ys.tail
        override type apply = xs.tail# ===[ys#tail]
    }
}


private[mada] final case class LessThan[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, Always0(`false`), Always0(`true`), Always0(`false`), xs.matchCaseCons(ys, XXTF(), XXTF(), FT(), XXTF())).apply.asInstanceOfBoolean.asInstanceOf[apply]
    type apply = xs.matchCaseNil[ys, Always0[`false`], Always0[`true`], Always0[`false`], xs#matchCaseCons[ys, XXTF, XXTF, FT, XXTF]]#apply#asInstanceOfBoolean

    final case class XXTF() extends Function0 {
        override  def self = this
        override type self = XXTF
        override  def apply = xs.tail < ys.tail
        override type apply = xs#tail# <[ys#tail]
    }

    final case class FT() extends Function0 {
        override  def self = this
        override type self = FT
        override  def apply = (ys.tail < xs.tail).not.asInstanceOf[apply]
        override type apply = ys#tail# <[xs#tail]#not
    }
}
