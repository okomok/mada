

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Flatten[xs <: Seq](xs: xs) extends AbstractSeq {
    type self = Flatten[xs]

    private lazy val ys: ys = new DropWhile(xs, new IsEmptyLocal)
    private type ys         =     DropWhile[xs,     IsEmptyLocal]

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    private lazy val localIter: localIter = ys.head.asInstanceOfSeq
    private type localIter                = ys#head#asInstanceOfSeq

    override  def head: head = localIter.head
    override type head       = localIter#head

    override  def tail: tail = new Flatten(new Append(localIter.tail, ys.tail))
    override type tail       =     Flatten[    Append[localIter#tail, ys#tail]]

    class IsEmptyLocal extends Function1 {
        type self = IsEmptyLocal
        override  def apply[xs <: Any](xs: xs): apply[xs] = xs.asInstanceOfSeq.isEmpty
        override type apply[xs <: Any]                    = xs#asInstanceOfSeq#isEmpty
    }
}
