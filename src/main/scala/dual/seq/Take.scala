

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Take[xs <: Seq, n <: Nat](xs: xs, n: n) extends AbstractSeq {
    type self = Take[xs, n]

    private lazy val ys: ys = `if`(n.isZero, Const0(Empty), Const0(xs)).apply
    private type ys         = `if`[n#isZero, Const0[Empty], Const0[xs]]#apply

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty        = ys#isEmpty

    override  def head: head = ys.head
    override type head        = ys#head

    override  def tail: tail = new Take(xs.tail, n.decrement)
    override type tail       =     Take[xs#tail, n#decrement]
}


final class TakeWhile[xs <: Seq, f <: Nat](xs: xs, n: n) extends AbstractSeq {
    type self = Take[xs, n]

    private lazy val ys: ys = `if`(xs.isEmpty, Const0(xs), Const0(xs)).apply
    private type ys         = `if`[n#isZero, Const0[Empty], Const0[xs]]#apply

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty        = ys#isEmpty

    override  def head: head = ys.head
    override type head        = ys#head

    override  def tail: tail = new Take(xs.tail, f)
    override type tail       =     Take[xs#tail, f]
}
