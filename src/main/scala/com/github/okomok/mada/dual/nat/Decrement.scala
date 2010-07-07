

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] final case class DecrementCons[x <: Boolean, xs <: Nat](x: x, xs: xs) {
     def apply: apply = xs.isEmpty.`if`(Always0(xs), x.`if`(Then(), Else())).apply.asInstanceOf
    type apply = xs#isEmpty#`if`[Always0[xs], x#`if`[Then, Else]]#apply#asInstanceOfNat

    // (`true` :: xs).decrement
    final case class Then() extends Function0 {
        override  def self = this
        override type self = Then
        override  def apply: apply = NatCons(`false`, xs)
        override type apply = NatCons[`false`, xs]
    }

    // (`false` :: xs).decrement
    final case class Else() extends Function0 {
        override  def self = this
        override type self = Else
        override  def apply: apply = NatCons(`true`, xs.decrement)
        override type apply = NatCons[`true`, xs#decrement]
    }
}
