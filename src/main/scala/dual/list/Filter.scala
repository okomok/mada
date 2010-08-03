

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Filter[xs <: List, f <: Function1](xs: xs, f: f) extends AbstractList {
    type self = Filter[xs, f]

    private lazy val ys: ys = new DropWhile(xs, f.not)
    private type ys         =     DropWhile[xs, f#not]

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    override  def head: head = ys.head
    override type head       = ys#head

    override  def tail: tail = new Filter(ys.tail, f)
    override type tail       =     Filter[ys#tail, f]
}
