

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.gxshub.okomok.mada
package dual; package seq


final class Map[xs <: Seq, f <: Function1](xs: xs, f: f) extends AbstractSeq {
    type self = Map[xs, f]

    override  def isEmpty: isEmpty = xs.isEmpty
    override type isEmpty          = xs#isEmpty

    override  def head: head = f.apply(xs.head)
    override type head       = f#apply[xs#head]

    override  def tail: tail = new Map(xs.tail, f)
    override type tail       =     Map[xs#tail, f]
}
