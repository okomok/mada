

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] object PrependReversed {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = ys.foldLeft_List(xs, step)
    type apply[xs <: List, ys <: List] = ys#foldLeft_List[xs, step]

    val step = new step
    class step extends Function2_List_Any_List {
        override  def apply[b <: List, a <: Any](b: b, a: a) = Cons(a, b)
        override type apply[b <: List, a <: Any] = Cons[a, b]
    }
}
