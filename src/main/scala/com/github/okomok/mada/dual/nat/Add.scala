

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] final case class Add[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
    @compilerWorkaround("2.8.0") // needed for some reason.
     def xsmc: xsmc = xs.matchCaseCons(ys, TT(), XF(), FX(), XF())
    type xsmc = xs#matchCaseCons[ys, TT, XF, FX, XF]

     def apply: apply = xs.matchCaseNil(ys, Always0(NatNil), Always0(ys), Always0(xs), xsmc).apply.asInstanceOfNat
    type apply = xs.matchCaseNil[ys, Always0[NatNil], Always0[ys], Always0[xs], xsmc]#apply#asInstanceOfNat

    final case class TT() extends Function0 {
        override  def self = this
        override type self = TT

        @compilerWorkaround("2.8.0") // needed for some reason.
         def yst: yst = ys.tail
        type yst = ys#tail

        override  def apply = NatCons(`false`, (xs.tail + yst).increment)
        override type apply = NatCons[`false`, xs#tail# +[yst]#increment]
    }

    final case class XF() extends Function0 {
        override  def self = this
        override type self = XF
        override  def apply = NatCons(xs.head, (xs.tail + ys.tail))
        override type apply = NatCons[xs#head, xs#tail# +[ys#tail]]
    }

    final case class FX() extends Function0 {
        override  def self = this
        override type self = FX
        override  def apply = NatCons(ys.head, xs.tail + ys.tail)
        override type apply = NatCons[ys#head, xs#tail# +[ys.tail]]
    }
}


/*
private[mada] final case class AddCons[xs <: Nat, ys <: Nat](xs: xs, ys: ys) {
     def apply/*: apply*/ = ys.isEmpty.`if`(Always0(xs), xs.matchCaseCons(ys, TT(), XF(), FX(), XF())).apply.asInstanceOfNat
    type apply = ys#isEmpty#`if`[Always0[xs], xs#matchCaseCons[ys, TT, XF, FX, XF]]#apply#asInstanceOfNat

    final case class TT() extends Function0 {
        override  def self = this
        override type self = TT

        @compilerWorkaround("2.8.0") // needed for some reason.
         val yst: yst = ys.tail
        type yst = ys#tail

        override  def apply = NatCons(`false`, (xs.tail + yst).increment)
        override type apply = NatCons[`false`, xs#tail# +[yst]#increment]
    }

    final case class XF() extends Function0 {
        override  def self = this
        override type self = XF
        override  def apply = NatCons(xs.head, (xs.tail + ys.tail))
        override type apply = NatCons[xs#head, xs#tail# +[ys#tail]]
    }

    final case class FX() extends Function0 {
        override  def self = this
        override type self = FX
        override  def apply = NatCons(ys.head, xs.tail + ys.tail)
        override type apply = NatCons[ys#head, xs#tail# +[ys.tail]]
    }
}
*/

/*
private[mada] final case class AddCons[x <: Boolean, xs <: Nat, ys <: Nat](x: x, xs: xs, ys: ys) {
     def apply: apply = ys.ifNil(Always0(NatCons(x, xs)), Else()).apply.asInstanceOfNat
    type apply = ys#ifNil[Always0[NatCons[x, xs]], Else]#apply#asInstanceOfNat

    // (x :: xs) + (z :: zs)
    final case class Else() extends Function0 {
        override  def self = this
        override type self = Else
        override  def apply: apply = ys.head.`if`(ElseThen(), ElseElse())
        override type apply = ys#head#`if`[ElseThen, ElseElse]
    }

        // (x :: xs) + (`false` :: zs)
        final case class ElseThen() extends Function0 {
            override  def self = this
            override type self = ElseThen
            override  def apply: apply = NatCons(x, (xs + ys))
            override type apply = NatCons[x, xs# +[ys]]
        }

        // (x :: xs) + (`true` :: zs)
        final case class ElseElse() extends Function0 {
            override  def self = this
            override type self = ElseElse
            override  def apply: apply = x.`if`(ElseElseThen(), ElseElseElse())
            override type apply = x#`if`[ElseElseThen, ElseElseElse]
        }

            // (`true` :: xs) + (`true` :: zs)
            final case class ElseElseThen() extends Function0 {
                override  def self = this
                override type self = ElseElseThen
                override  def apply: apply = NatCons(`false`, (xs + ys).increment)
                override type apply = NatCons[`false`, xs# +[ys]#increment]
            }

            // (`false` :: xs) + (`true` :: zs)
            final case class ElseElseElse() extends Function0 {
                override  def self = this
                override type self = ElseElseElse
                override  def apply: apply = NatCons(ys.head, xs + ys.tail)
                override type apply = NatCons[ys#head, xs# +[ys.tail]]
            }
}
*/
