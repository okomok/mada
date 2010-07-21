

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsFind {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] =
        `if`(f.apply(x).asInstanceOfBoolean, const0(Some(x)), Else(x, xs, f)).apply.asInstanceOfOption
    type apply[x <: Any, xs <: List, f <: Function1] =
        `if`[f#apply[x]#asInstanceOfBoolean, const0[Some[x]], Else[x, xs, f]]#apply#asInstanceOfOption

    case class Else[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Else[x, xs, f]
        override  def apply: apply = xs.find(f)
        override type apply = xs#find[f]
    }
}
