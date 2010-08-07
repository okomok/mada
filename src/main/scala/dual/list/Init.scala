

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Init {
     def apply[xs <: List](xs: xs): apply[xs] = new Impl(xs)
    type apply[xs <: List]                    =     Impl[xs]

    class Impl[xs <: List](xs: xs) extends AbstractList {
        type self = Impl[xs]

        private lazy val ys: ys = `if`(xs.tail.isEmpty, const0(Nil), const0(xs)).apply.asInstanceOfList
        private type ys         = `if`[xs#tail#isEmpty, const0[Nil], const0[xs]]#apply#asInstanceOfList

        override  def isEmpty: isEmpty = ys.isEmpty
        override type isEmpty          = ys#isEmpty

        override  def head: head = ys.head
        override type head       = ys#head

        override  def tail: tail = new Impl(ys.tail)
        override type tail       =     Impl[ys#tail]
    }
}
