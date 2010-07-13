

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class Filter {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] = `if`(xs.isEmpty, always0(xs), Else(xs, f)).apply.asInstanceOfList
    type apply[xs <: List, f <: Function1] = `if`[xs#isEmpty, always0[xs], Else[xs, f]]#apply#asInstanceOfList

    final case class Else[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = Else[xs, f]
        override  def apply: apply = `if`(f.apply(xs.head).asInstanceOfBoolean, ElseThen(xs, f), ElseElse(xs, f)).apply.asInstanceOf[apply]
        override type apply = `if`[f#apply[xs#head]#asInstanceOfBoolean, ElseThen[xs, f], ElseElse[xs, f]]#apply
    }

    final case class ElseThen[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = ElseThen[xs, f]
        override  def apply: apply = Cons(xs.head, new Filter().apply(xs.tail, f)).asInstanceOf[apply]
        override type apply = Cons[xs#head, Filter#apply[xs#tail, f]]
    }
    final case class ElseElse[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        override  val self = this
        override type self = ElseElse[xs, f]
        override  def apply: apply = new Filter().apply(xs.tail, f).asInstanceOf[apply]
        override type apply = Filter#apply[xs#tail, f]
    }
}
