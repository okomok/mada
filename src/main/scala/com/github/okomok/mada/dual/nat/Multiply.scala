

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] class Multiply {
     def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = x.foldRight(Zero, new Step(y)).asInstanceOfNat
    type apply[x <: Nat, y <: Nat] = x#foldRight[Zero, Step[y]]#asInstanceOfNat

    class Step[y <: Nat](y: y) extends Function2 {
        override def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = y + b.asInstanceOfNat
        override type apply[a <: Any, b <: Any] = y# +[b#asInstanceOfNat]
    }
}
