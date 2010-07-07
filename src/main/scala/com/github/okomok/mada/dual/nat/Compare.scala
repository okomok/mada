

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
        override type apply =xs.tail# ===[ys#tail]
    }
}

private[mada] final case class Lteq[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, Always0(`true`), Always0(`true`), Always0(`false`), CC()).apply.asInstanceOfBoolean
    type apply = xs#matchCaseNil[ys, Always0[`true`], Always0[`true`], Always0[`false`], CC]#apply#asInstanceOfBoolean

    final case class CC() extends Function0 {
        override  def self = this
        override type self = CC
        override  def apply = xs.decrement <= ys.decrement
        override type apply = xs#decrement# <=[ys#decrement]
    }
}

private[mada] final case class Lt[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, Always0(`false`), Always0(`true`), Always0(`false`), CC()).apply.asInstanceOfBoolean
    type apply = xs#matchCaseNil[ys, Always0[`false`], Always0[`true`], Always0[`false`], CC]#apply#asInstanceOfBoolean

    final case class CC() extends Function0 {
        override  def self = this
        override type self = CC
        override  def apply = xs.decrement < ys.decrement
        override type apply = xs#decrement# <[ys#decrement]
    }
}
