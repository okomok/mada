

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final case class Filter[xs <: List, f <: Function1](xs: xs, f: f) {
     def apply: apply = `if`(xs.isEmpty, always0(xs), Else()).apply.asInstanceOfList
    type apply = `if`[xs#isEmpty, always0[xs], Else]#apply#asInstanceOfList

    final case class Else() extends Function0 {
        override  val self = this
        override type self = Else
        override  def apply: apply = `if`(f.apply(xs.head).asInstanceOfBoolean, ElseThen(), ElseElse()).apply.asInstanceOf[apply]
        override type apply = `if`[f#apply[xs#head]#asInstanceOfBoolean, ElseThen, ElseElse]#apply
    }

    final case class ElseThen() extends Function0 {
        override  val self = this
        override type self = ElseThen
        override  def apply: apply = Cons(xs.head, Filter(xs.tail, f).apply).asInstanceOf[apply]
        override type apply = Cons[xs#head, Filter[xs#tail, f]#apply]
    }
    final case class ElseElse() extends Function0 {
        override  val self = this
        override type self = ElseElse
        override  def apply: apply = Filter(xs.tail, f).apply.asInstanceOf[apply]
        override type apply = Filter[xs#tail, f]#apply
    }
}

/*
private[mada] class FilterCons {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] = `if`(f.apply(x).asInstanceOfBoolean, Then(x, xs, f), Else(x, xs, f)).apply.asInstanceOfList
    type apply[x <: Any, xs <: List, f <: Function1] = `if`[f#apply[x]#asInstanceOfBoolean, Then[x, xs, f], Else[x, xs, f]]#apply#asInstanceOfList

    final case class Then[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Then[x, xs, f]
        override  def apply: apply = Cons(x, xs.filter(f))
        override type apply = Cons[x, xs#filter[f]]
    }
    final case class Else[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Else[x, xs, f]
        override  def apply: apply = xs.filter(f)
        override type apply = xs#filter[f]
    }
}
*/
