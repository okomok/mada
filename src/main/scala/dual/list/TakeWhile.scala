

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsTakeWhile {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] =
        `if`(f.apply(x).asInstanceOfBoolean, Then(x, xs, f), Const0(Nil)).apply.asInstanceOfList
    type apply[x <: Any, xs <: List, f <: Function1] =
        `if`[f#apply[x]#asInstanceOfBoolean, Then[x, xs, f], Const0[Nil]]#apply#asInstanceOfList

    case class Then[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        type self = Then[x, xs, f]
        override  def apply: apply = Cons(x, xs.takeWhile(f))
        override type apply = Cons[x, xs#takeWhile[f]]
    }
}
