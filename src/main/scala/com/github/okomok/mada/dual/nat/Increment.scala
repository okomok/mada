

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] final case class IncrementCons[x <: Boolean, xs <: Nat](x: x, xs: xs) {
    Predef.assert(null != x)
    Predef.assert(null != xs)
     def apply: apply = x.`if`(Then(), Else()).apply.asInstanceOfNat
    type apply = x#`if`[Then, Else]#apply#asInstanceOfNat

    // (`true` :: xs).increment
    final case class Then() extends Function0 {
        override  def self = this
        override type self = Then
        override  def apply: apply = NatCons(`false`, xs.increment)
        override type apply = NatCons[`false`, xs#increment]
    }

    // (`false` :: xs).increment
    final case class Else() extends Function0 {
        override  def self = this
        override type self = Else
        override  def apply: apply = NatCons(`true`, xs)
        override type apply = NatCons[`true`, xs]
    }
}
