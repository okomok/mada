

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Append[xs <: List, ys <: List](xs: xs, ys: ys) extends AbstractList {
    type self = Append[xs, ys]

    override  def isEmpty: isEmpty = xs.isEmpty  && ys.isEmpty
    override type isEmpty          = xs#isEmpty# &&[ys#isEmpty]

    override  def head: head = `if`(xs.isEmpty, new DerefThen, new DerefElse).apply
    override type head       = `if`[xs#isEmpty,     DerefThen,     DerefElse]#apply

    override  def tail: tail = `if`(xs.isEmpty, new NextThen, new NextElse).apply.asInstanceOfList
    override type tail       = `if`[xs#isEmpty,     NextThen,     NextElse]#apply#asInstanceOfList

    class DerefThen extends Function0 {
        type self = DerefThen
        override  def apply: apply = ys.head
        override type apply        = ys#head
    }

    class DerefElse extends Function0 {
        type self = DerefElse
        override  def apply: apply = xs.head
        override type apply        = xs#head
    }

    class NextThen extends Function0 {
        type self = NextThen
        override  def apply: apply = new Append(xs, ys.tail)
        override type apply        =     Append[xs, ys#tail]
    }

    class NextElse extends Function0 {
        type self = NextElse
        override  def apply: apply = new Append(xs.tail, ys)
        override type apply        =     Append[xs#tail, ys]
    }
}
