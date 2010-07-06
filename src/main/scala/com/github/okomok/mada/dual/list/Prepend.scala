

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] class Prepend {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = ys.foldRight(xs, step).asInstanceOfList
    type apply[xs <: List, ys <: List] = ys#foldRight[xs, step]#asInstanceOfList

    val step = new step
    final class step extends Function2 {
        override  def self = this
        override type self = step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = Cons(a, b.asInstanceOfList)
        override type apply[a <: Any, b <: Any] = Cons[a, b#asInstanceOfList]
    }
}
