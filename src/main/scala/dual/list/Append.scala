

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Append {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = Impl(xs, ys)
    type apply[xs <: List, ys <: List]                                = Impl[xs, ys]

    case class Impl[xs <: List, ys <: List](xs: xs, ys: ys) extends AbstractList {
        type self = Impl[xs, ys]

        override  def isEmpty: isEmpty = xs.isEmpty.and(ys.isEmpty)
        override type isEmpty          = xs#isEmpty#and[ys#isEmpty]

        override  def head: head = `if`(xs.isEmpty, DerefThen(xs, ys), DerefElse(xs, ys)).apply
        override type head       = `if`[xs#isEmpty, DerefThen[xs, ys], DerefElse[xs, ys]]#apply

        override  def tail: tail = `if`(xs.isEmpty, NextThen(xs, ys), NextElse(xs, ys)).apply.asInstanceOfList
        override type tail       = `if`[xs#isEmpty, NextThen[xs, ys], NextElse[xs, ys]]#apply#asInstanceOfList
    }

    case class DerefThen[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = DerefThen[xs, ys]
        override  def apply: apply = ys.head
        override type apply        = ys#head
    }

    case class DerefElse[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = DerefElse[xs, ys]
        override  def apply: apply = xs.head
        override type apply        = xs#head
    }

    case class NextThen[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = NextThen[xs, ys]
        override  def apply: apply = Append.apply(xs, ys.tail)
        override type apply        = Append.apply[xs, ys#tail]
    }

    case class NextElse[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = NextElse[xs, ys]
        override  def apply: apply = Append.apply(xs.tail, ys)
        override type apply        = Append.apply[xs#tail, ys]
    }
}