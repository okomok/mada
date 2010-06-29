

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] object Multiply extends Function2[Nat, Nat, Nat] {
    override def apply[x <: Nat, y <: Nat](x: x, y: y): apply[x, y] = x.foldRight_Nat(zero, new step(y))
    override type apply[x <: Nat, y <: Nat] = x#foldRight_Nat[zero, step[y]]

    class step[y <: Nat](y: y) extends Function2[Nat, Nat, Nat] {
        override def apply[a <: Nat, b <: Nat](a: a, b: b): apply[a, b] = y + b
        override type apply[a <: Nat, b <: Nat] = y# +[b]
    }
}
