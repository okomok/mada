

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] object Drop {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = n.foldRight_List(xs, step)
    type apply[xs <: List, n <: Nat] = n#foldRight_List[xs, step]

    val step = new step
    class step extends Function2_Nat_List_List {
        override  def apply[a <: Nat, b <: List](a: a, b: b): apply[a, b] = b.tail
        override type apply[a <: Nat, b <: List] = b#tail
    }
}
