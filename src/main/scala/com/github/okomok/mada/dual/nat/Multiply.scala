

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] class Multiply {
     def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = x.foldRight_Nat(Zero, new Step(y))
    type apply[x <: Nat, y <: Nat] = x#foldRight_Nat[Zero, Step[y]]

    class Step[y <: Nat](y: y) extends Function2_Nat_Nat_Nat {
        override def apply[a <: Nat, b <: Nat](a: a, b: b): apply[a, b] = y + b
        override type apply[a <: Nat, b <: Nat] = y# +[b]
    }
}
