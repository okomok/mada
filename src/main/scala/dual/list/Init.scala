

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsInit {
     def apply[x <: Any, xs <: List](x: x, xs: xs): apply[x, xs] =
        `if`(xs.isEmpty, const0(Nil), Else(x, xs)).apply.asInstanceOfList//.asInstanceOf[apply[x, xs]]
    type apply[x <: Any, xs <: List] =
        `if`[xs#isEmpty, const0[Nil], Else[x, xs]]#apply#asInstanceOfList

    case class Else[x <: Any, xs <: List](x: x, xs: xs) extends Function0 {
        override  val self = this
        override type self = Else[x, xs]
        override  def apply: apply = Cons(x, xs.init)
        override type apply = Cons[x, xs.init]
    }
}
