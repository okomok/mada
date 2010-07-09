

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] class FilterCons {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] = f.apply(x).asInstanceOfBoolean.`if`(Then(x, xs, f), Else(x, xs, f)).apply.asInstanceOfList
    type apply[x <: Any, xs <: List, f <: Function1] =f#apply[x]#asInstanceOfBoolean#`if`[Then[x, xs, f], Else[x, xs, f]]#apply#asInstanceOfList

    final case class Then[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  def self = this
        override type self = Then[x, xs, f]
        override  def apply: apply = Cons(x, xs.filter(f))
        override type apply = Cons[x, xs#filter[f]]
    }
    final case class Else[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  def self = this
        override type self = Else[x, xs, f]
        override  def apply: apply = xs.filter(f)
        override type apply = xs#filter[f]
    }
}
