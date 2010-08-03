

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Zip[xs <: List, ys <: List](xs: xs, ys: ys) extends AbstractList {
    type self = Zip[xs, ys]

    override  def isEmpty: isEmpty = xs.isEmpty  || ys.isEmpty
    override type isEmpty          = xs#isEmpty# ||[ys#isEmpty]

    override  def head: head = Tuple2(xs.head, ys.head)
    override type head       = Tuple2[xs#head, ys#head]

    override  def tail: tail = new Zip(xs.tail, ys.tail)
    override type tail       =     Zip[xs#tail, ys#tail]
}
