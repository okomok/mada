

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Take {
     def apply[xs <: List, n <: Nat](xs: xs, n: n): apply[xs, n] = new Impl(xs, n)
    type apply[xs <: List, n <: Nat]                             =     Impl[xs, n]

    class Impl[xs <: List, n <: Nat](xs: xs, n: n) extends AbstractList {
        type self = Impl[xs, n]

        private lazy val ys: ys = `if`(n.isZero, const0(Nil), const0(xs)).apply.asInstanceOfList
        private type ys         = `if`[n#isZero, const0[Nil], const0[xs]]#apply#asInstanceOfList

        override  def isEmpty: isEmpty = ys.isEmpty
        override type isEmpty          = ys#isEmpty

        override  def head: head = ys.head
        override type head       = ys#head

        override  def tail: tail = new Impl(ys.tail, n.decrement)
        override type tail       =     Impl[ys#tail, n#decrement]
    }
}


private[dual]
object TakeWhile {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] = new Impl(xs, f)
    type apply[xs <: List, f <: Function1]                             =     Impl[xs, f]

    class Impl[xs <: List, f <: Function1](xs: xs, f: f) extends AbstractList {
        type self = Impl[xs, f]

        private lazy val ys: ys = `if`(xs.isEmpty, const0(xs), new Else(xs, f)).apply.asInstanceOfList
        private type ys         = `if`[xs#isEmpty, const0[xs],     Else[xs, f]]#apply#asInstanceOfList

        override  def isEmpty: isEmpty = ys.isEmpty
        override type isEmpty          = ys#isEmpty

        override  def head: head = ys.head
        override type head       = ys#head

        override  def tail: tail = new Impl(ys.tail, f)
        override type tail       =     Impl[ys#tail, f]
    }

    class Else[xs <: List, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply = `if`(f.apply(xs.head).asInstanceOfBoolean, const0(xs), const0(Nil)).apply.asInstanceOf[apply]
        override type apply        = `if`[f#apply[xs#head]#asInstanceOfBoolean, const0[xs], const0[Nil]]#apply
    }
}
