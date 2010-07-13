

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] class PrependReversed {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = ys.foldLeft(xs, Step()).asInstanceOfList
    type apply[xs <: List, ys <: List] = ys#foldLeft[xs, Step]#asInstanceOfList

    final case class Step() extends Function2 {
        override  val self = this
        override type self = Step
        override  def apply[b <: Any, a <: Any](b: b, a: a): apply[b, a] = Cons(a, b.asInstanceOfList)
        override type apply[b <: Any, a <: Any] = Cons[a, b#asInstanceOfList]
    }
}
