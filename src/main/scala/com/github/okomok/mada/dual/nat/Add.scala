

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] object Plus {
     def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = x.foldRight_Nat(y, step)
    type apply[x <: Nat, y <: Nat] = x#foldRight_Nat[y, step]

    val step = new step
    class step extends Function2[Nat, Nat, Nat] {
        override  def apply[a <: Nat, b <: Nat](a: a, b: b): apply[a, b] = b.increment
        override type apply[a <: Nat, b <: Nat] = b#increment
    }
}
