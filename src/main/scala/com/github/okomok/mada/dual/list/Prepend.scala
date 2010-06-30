

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] object Prepend {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = ys.foldRight_List(xs, step)
    type apply[xs <: List, ys <: List] = ys#foldRight_List[xs, step]

    val step = new step
    class step extends Function2_Any_List_List {
        override  def apply[a <: Any, b <: List](a: a, b: b) = Cons(a, b)
        override type apply[a <: Any, b <: List] = Cons[a, b]
    }
}
