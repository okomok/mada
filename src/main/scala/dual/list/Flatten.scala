

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Flatten[xs <: List](xs: xs) extends AbstractList {
    type self = Flatten[xs]

    private lazy val ys: ys = new DropWhile(xs, new IsEmpty)
    private type ys         =     DropWhile[xs,     IsEmpty]

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    private lazy val local: local = ys.head.asInstanceOfList
    private type local            = ys#head#asInstanceOfList

    override  def head: head = local.head
    override type head       = local#head

    override  def tail: tail = new Flatten(new Cons(local.tail, ys.tail))
    override type tail       =     Flatten[    Cons[local#tail, ys#tail]]

    class IsEmpty extends Function1 {
        type self = IsEmpty
        override  def apply[xs <: Any](xs: xs): apply[xs] = xs.asInstanceOfList.isEmpty
        override type apply[xs <: Any]                    = xs#asInstanceOfList#isEmpty
    }
}
