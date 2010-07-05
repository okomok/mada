

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


@compilerWorkaround("2.8.0") // `object Add` crashes `Operator`.
private[mada] class Add {
     def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = x.foldRight(y, step).asInstanceOfNat
    type apply[x <: Nat, y <: Nat] = x#foldRight[y, step]#asInstanceOfNat

    val step = new step
    class step extends Function2 {
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = b.asInstanceOfNat.increment
        override type apply[a <: Any, b <: Any] = b#asInstanceOfNat#increment
    }
}
