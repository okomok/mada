

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[dual]
object Sort {
     def apply[xs <: List, o <: Ordering](xs: xs, o: o): apply[xs, o] =
        `if`(xs.isEmpty, const0(xs), new Else(xs, o)).apply.asInstanceOfList.asInstanceOf[apply[xs, o]]
    type apply[xs <: List, o <: Ordering] =
        `if`[xs#isEmpty, const0[xs],     Else[xs, o]]#apply#asInstanceOfList

    class Else[xs <: List, o <: Ordering](xs: xs, o: o) extends Function0 {
        type self = Else[xs, o]
        override  def apply: apply = `if`(xs.tail.isEmpty, const0(xs), new ElseElse(xs, o)).apply.asInstanceOfList//.asInstanceOf[apply]
        override type apply        = `if`[xs#tail#isEmpty, const0[xs],     ElseElse[xs, o]]#apply#asInstanceOfList
    }

    class ElseElse[xs <: List, o <: Ordering](xs: xs, o: o) extends Function0 {
        type self = ElseElse[xs, o]
        private lazy val r: r = xs.splitAt(xs.length  / nat.peano._2)
        private type r        = xs#splitAt[xs#length# /[nat.peano._2]]
        override  def apply: apply = Merge.apply(r._1.asInstanceOfList.sort(o), r._2.asInstanceOfList.sort(o), o).asInstanceOf[apply]
        override type apply        = Merge.apply[r#_1#asInstanceOfList#sort[o], r#_2#asInstanceOfList#sort[o], o]
    }
}


private[dual]
object Merge {
     def apply[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o): apply[xs, ys, o] =
        `if`(xs.isEmpty, const0(ys), `if`(ys.isEmpty, const0(xs), new Else(xs, ys, o))).apply.asInstanceOfList.asInstanceOf[apply[xs, ys, o]]
    type apply[xs <: List, ys <: List, o <: Ordering] =
        `if`[xs#isEmpty, const0[ys], `if`[ys#isEmpty, const0[xs],     Else[xs, ys, o]]]#apply#asInstanceOfList

    class Else[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
        type self = Else[xs, ys, o]
        override  def apply: apply = `if`(o.lteq(xs.head, ys.head), new ElseThen(xs, ys, o), new ElseElse(xs, ys, o)).apply.asInstanceOf[apply]
        override type apply        = `if`[o#lteq[xs#head, ys#head],     ElseThen[xs, ys, o],     ElseElse[xs, ys, o]]#apply
    }

    class ElseThen[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
        type self = ElseThen[xs, ys, o]
        override  def apply: apply = new Cons(xs.head, Merge.apply(xs.tail, ys, o)).asInstanceOf[apply]
        override type apply        =     Cons[xs#head, Merge.apply[xs#tail, ys, o]]
    }

    class ElseElse[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
        type self = ElseElse[xs, ys, o]
        override  def apply: apply = new Cons(ys.head, Merge.apply(xs, ys.tail, o)).asInstanceOf[apply]
        override type apply        =     Cons[ys#head, Merge.apply[xs, ys#tail, o]]
    }
}
