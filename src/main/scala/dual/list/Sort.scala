

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Sort[xs <: List, o <: Ordering](xs: xs, o: o) extends TrivialForwarder {
    type self = Sort[xs, o]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, const0(xs), new Else).apply.asInstanceOfList.asInstanceOf[delegate]
    override protected type delegate =
        `if`[xs#isEmpty, const0[xs],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(xs.tail.isEmpty, const0(xs), new ElseElse).apply.asInstanceOfList//.asInstanceOf[apply]
        override type apply        = `if`[xs#tail#isEmpty, const0[xs],     ElseElse]#apply#asInstanceOfList
    }

    class ElseElse extends Function0 {
        type self = ElseElse
        private lazy val r: r = xs.splitAt(xs.length  / nat.peano._2)
        private type r        = xs#splitAt[xs#length# /[nat.peano._2]]
        override  def apply: apply = new Merge(r._1.asInstanceOfList.sort(o), r._2.asInstanceOfList.sort(o), o).apply.asInstanceOf[apply]
        override type apply        =     Merge[r#_1#asInstanceOfList#sort[o], r#_2#asInstanceOfList#sort[o], o]#apply
    }
}


final class Merge[xs <: List, ys <: List, o <: Ordering](xs: xs, ys: ys, o: o) extends Function0 {
    type self = Merge[xs, ys, o]

    override  def apply: apply =
        `if`(xs.isEmpty, const0(ys), `if`(ys.isEmpty, const0(xs), new Else)).apply.asInstanceOfList.asInstanceOf[apply]
    override type apply =
        `if`[xs#isEmpty, const0[ys], `if`[ys#isEmpty, const0[xs],     Else]]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(o.lteq(xs.head, ys.head), new ElseThen, new ElseElse).apply.asInstanceOf[apply]
        override type apply        = `if`[o#lteq[xs#head, ys#head],     ElseThen,     ElseElse]#apply
    }

    class ElseThen extends Function0 {
        type self = ElseThen
        override  def apply: apply = new Cons(xs.head, new Merge(xs.tail, ys, o).apply).asInstanceOf[apply]
        override type apply        =     Cons[xs#head,     Merge[xs#tail, ys, o]#apply]
    }

    class ElseElse extends Function0 {
        type self = ElseElse
        override  def apply: apply = new Cons(ys.head, new Merge(xs, ys.tail, o).apply).asInstanceOf[apply]
        override type apply        =     Cons[ys#head,     Merge[xs, ys#tail, o]#apply]
    }
}
