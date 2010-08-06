

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
final class Step[xs <: List, n <: Nat](xs: xs, n: n) extends AbstractList {
    type self = Step[xs, n]

    override  def isEmpty: isEmpty = xs.isEmpty
    override type isEmpty          = xs#isEmpty

    override  def head: head = xs.head
    override type head       = xs#head

    override  def tail: tail = new Step(xs.drop(n), n)
    override type tail       =     Step[xs#drop[n], n]
}
