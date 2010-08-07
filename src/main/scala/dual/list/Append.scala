

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Append {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] = new Impl(xs, ys)
    type apply[xs <: List, ys <: List]                                =     Impl[xs, ys]

    class Impl[xs <: List, ys <: List](xs: xs, ys: ys) extends AbstractList {
        type self = Impl[xs, ys]

        override  def isEmpty: isEmpty = xs.isEmpty.and(ys.isEmpty)
        override type isEmpty          = xs#isEmpty#and[ys#isEmpty]

        override  def head: head = `if`(xs.isEmpty, new DerefThen(xs, ys), new DerefElse(xs, ys)).apply
        override type head       = `if`[xs#isEmpty,     DerefThen[xs, ys],     DerefElse[xs, ys]]#apply

        override  def tail: tail = `if`(xs.isEmpty, new NextThen(xs, ys), new NextElse(xs, ys)).apply.asInstanceOfList
        override type tail       = `if`[xs#isEmpty,     NextThen[xs, ys],     NextElse[xs, ys]]#apply#asInstanceOfList
    }

    class DerefThen[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = DerefThen[xs, ys]
        override  def apply: apply = ys.head
        override type apply        = ys#head
    }

    class DerefElse[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = DerefElse[xs, ys]
        override  def apply: apply = xs.head
        override type apply        = xs#head
    }

    class NextThen[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = NextThen[xs, ys]
        override  def apply: apply = Append.apply(xs, ys.tail)
        override type apply        = Append.apply[xs, ys#tail]
    }

    class NextElse[xs <: List, ys <: List](xs: xs, ys: ys) extends Function0 {
        type self = NextElse[xs, ys]
        override  def apply: apply = Append.apply(xs.tail, ys)
        override type apply        = Append.apply[xs#tail, ys]
    }
}
