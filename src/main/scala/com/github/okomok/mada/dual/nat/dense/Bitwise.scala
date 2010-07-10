

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final case class BitAnd[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, always0(Nil), always0(Nil), always0(Nil), xs.matchCaseCons(ys, TT(), Else(), Else(), Else())).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = xs.matchCaseNil[ys, always0[Nil], always0[Nil], always0[Nil], xs#matchCaseCons[ys, TT, Else, Else, Else]]#apply#asInstanceOfNatDense

    final case class TT() extends Function0 {
        override  def self = this
        override type self = TT
        override  def apply: apply = Cons(`true`, xs.tail & ys.tail)
        override type apply = Cons[`true`, xs#tail# &[ys#tail]]
    }

    final case class Else() extends Function0 {
        override  def self = this
        override type self = Else
        override  def apply: apply = ConsFalse(xs.tail & ys.tail).apply.asInstanceOf[apply]
        override type apply = ConsFalse[xs#tail# &[ys#tail]]#apply
    }
}


private[mada] final case class BitOr[xs <: Dense, ys <: Dense](xs: xs, ys: ys) {
     def apply: apply = xs.matchCaseNil(ys, always0(Nil), always0(ys), always0(xs), xs.matchCaseCons(ys, Else(), Else(), Else(), FF())).apply.asInstanceOfNatDense.asInstanceOf[apply]
    type apply = xs.matchCaseNil[ys, always0[Nil], always0[ys], always0[xs], xs#matchCaseCons[ys, Else, Else, Else, FF]]#apply#asInstanceOfNatDense

    final case class FF() extends Function0 {
        override  def self = this
        override type self = FF
        override  def apply: apply = Cons(`false`, xs.tail | ys.tail)
        override type apply = Cons[`false`, xs#tail# |[ys#tail]]
    }

    final case class Else() extends Function0 {
        override  def self = this
        override type self = Else
        override  def apply: apply = Cons(`true`, xs.tail | ys.tail)
        override type apply = Cons[`true`, xs#tail# |[ys#tail]]
    }
}
