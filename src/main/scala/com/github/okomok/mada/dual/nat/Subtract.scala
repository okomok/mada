

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] object Subtract extends Function2[Nat, Nat, Nat] {
    override  def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = y.foldRight_Nat(x, step)
    override type apply[x <: Nat, y <: Nat] = y#foldRight_Nat[x, step]

    val step = new step
    class step extends Function2[Nat, Nat, Nat] {
        override  def apply[a <: Nat, b <: Nat](a: a, b: b): apply[a, b] = b.decrement
        override type apply[a <: Nat, b <: Nat] = b#decrement
    }
}
