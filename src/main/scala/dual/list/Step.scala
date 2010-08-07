

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Step {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = new Impl(xs, n)
    type apply[xs <: List, n <: Nat]                             =     Impl[xs, n]

    class Impl[xs <: List, n <: Nat](xs: xs, n: n) extends AbstractList {
        type self = Impl[xs, n]

        override  def isEmpty: isEmpty = xs.isEmpty
        override type isEmpty          = xs#isEmpty

        override  def head: head = xs.head
        override type head       = xs#head

        override  def tail: tail = new Impl(xs.drop(n), n)
        override type tail       =     Impl[xs#drop[n], n]
    }
}
