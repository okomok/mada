

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class ConsSort {
     def apply[xs <: List, o <: Ordering](xs: xs, o: o): apply[xs, o] =
        `if`(xs.tail.isEmpty, const0(xs), Else(xs, o)).apply.asInstanceOfList//.asInstanceOf[apply[xs, o]]
    type apply[xs <: List, o <: Ordering] =
        `if`[xs#tail#isEmpty, const0[xs], Else[xs, o]]#apply#asInstanceOfList

    case class Else[xs <: List, o <: Ordering](xs: xs, o: o) extends Function0 {
        type self = Else[xs, o]
        override  def apply: apply = new Merge().apply(r._1.asInstanceOfList.sort(o), r._2.asInstanceOfList.sort(o), o)
        override type apply = Merge#apply[r#_1#asInstanceOfList#sort[o], r#_2#asInstanceOfList#sort[o], o]
        private lazy val r: r = xs.splitAt(xs.size / nat.peano._2)
        private type r = xs#splitAt[xs#size# /[nat.peano._2]]
    }
}


private[mada] final class Merge {
     def apply[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o): apply[xs, ys, o] =
        `if`(xs.isEmpty, const0(ys), `if`(ys.isEmpty, const0(xs), Else(xs, ys, o))).apply.asInstanceOfList.asInstanceOf[apply[xs, ys, o]]
    type apply[xs <: List, ys <: List, o <: Ordering] =
        `if`[xs#isEmpty, const0[ys], `if`[ys#isEmpty, const0[xs], Else[xs, ys, o]]]#apply#asInstanceOfList

    case class Else[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
        type self = Else[xs, ys, o]
        override  def apply: apply = `if`(o.lteq(xs.head, ys.head), ElseThen(xs, ys, o), ElseElse(xs, ys, o)).apply.asInstanceOf[apply]
        override type apply        = `if`[o#lteq[xs#head, ys#head], ElseThen[xs, ys, o], ElseElse[xs, ys, o]]#apply
    }

    case class ElseThen[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
        type self = ElseThen[xs, ys, o]
        override  def apply: apply = Cons(xs.head, new Merge().apply(xs.tail, ys, o))
        override type apply = Cons[xs#head, Merge#apply[xs#tail, ys, o]]
    }

    case class ElseElse[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
        type self = ElseElse[xs, ys, o]
        override  def apply: apply = Cons(ys.head, new Merge().apply(xs, ys.tail, o))
        override type apply = Cons[ys#head, Merge#apply[xs, ys#tail, o]]
    }
}
