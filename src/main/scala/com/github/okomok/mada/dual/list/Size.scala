

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] class Size {
     def apply[xs <: List](xs: xs): apply[xs] = xs.foldRight_Nat(_0N, step)
    type apply[xs <: List] = xs#foldRight_Nat[_0N, step]

    val step = new step
    class step extends Function2_Any_Nat_Nat {
        override  def apply[a <: Any, b <: Nat](a: a, b: b): apply[a, b] = b.increment
        override type apply[a <: Any, b <: Nat] = b#increment
    }
}
