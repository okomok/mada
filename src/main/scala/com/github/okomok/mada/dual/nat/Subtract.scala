

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] class Subtract {
     def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = y.foldRight(x, step).asInstanceOfNat
    type apply[x <: Nat, y <: Nat] = y#foldRight[x, step]#asInstanceOfNat

    val step = new step
    class step extends Function2 {
        override  def self = this
        override type self = step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNat.decrement
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNat#decrement
    }
}
