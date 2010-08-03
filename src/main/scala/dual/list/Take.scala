

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Take[xs <: List, n <: Nat](xs: xs, n: n) extends AbstractList {
    type self = Take[xs, n]

    private lazy val ys: ys = `if`(n.isZero, Const0(Nil), Const0(xs)).apply.asInstanceOfList
    private type ys         = `if`[n#isZero, Const0[Nil], Const0[xs]]#apply#asInstanceOfList

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    override  def head: head = ys.head
    override type head       = ys#head

    override  def tail: tail = new Take(ys.tail, n.decrement)
    override type tail       =     Take[ys#tail, n#decrement]
}


final class TakeWhile[xs <: List, f <: Function1](xs: xs, f: f) extends AbstractList {
    type self = TakeWhile[xs, f]

    private lazy val ys: ys = `if`(xs.isEmpty, Const0(xs), new Else).apply.asInstanceOfList
    private type ys         = `if`[xs#isEmpty, Const0[xs],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(f.apply(xs.head).asInstanceOfBoolean, Const0(xs), Const0(Nil)).apply.asInstanceOf[apply]
        override type apply        = `if`[f#apply[xs#head]#asInstanceOfBoolean, Const0[xs], Const0[Nil]]#apply
    }

    override  def isEmpty: isEmpty = ys.isEmpty
    override type isEmpty          = ys#isEmpty

    override  def head: head = ys.head
    override type head       = ys#head

    override  def tail: tail = new TakeWhile(ys.tail, f)
    override type tail       =     TakeWhile[ys#tail, f]
}
