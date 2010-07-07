

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


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
