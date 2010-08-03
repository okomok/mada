

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Init[xs <: Seq](xs: xs) extends AbstractSeq {
    type self = Init[xs]

    private lazy val ys: ys = `if`(xs.tail.isEmpty, Const0(Nil), Const0(xs)).apply.asInstanceOfSeq
    private type ys         = `if`[xs#tail#isEmpty, Const0[Nil], Const0[xs]]#apply#asInstanceOfSeq

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    override  def head: head = ys.head
    override type head       = ys#head

    override  def tail: tail = new Init(ys.tail)
    override type tail       =     Init[ys#tail]
}
