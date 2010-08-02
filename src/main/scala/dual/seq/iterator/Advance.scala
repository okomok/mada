

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package iterator


private[mada] final class Advance {
     def apply[it <: Iterator, n <: Nat](it: it, n: n): apply[n] =
        `if`(it.isEnd || n.isZero,  Const0(End), Else(it, n)).apply.asInstanceOfSeqIterator
    type apply[it <: Iterator, n <: Nat] =
        `if`[it#isEnd ||[n#isZero], Const0[End], Else[it, n]]#apply#asInstanceOfSeqIterator

    case class Else[it <: Iterator, n <: Nat](it: it, n: n) extends Function0 {
        type self = Else[it, n]
        override  def apply: apply = it.next.advance(n.decrement)
        override type apply        = it#next#advance[n#decrement]
    }
}


private[mada] final class AdvanceWhile {
     def apply[it <: Iterator, f <: Function1](it: it, f: f): apply[f] =
        `if`(it.isEnd, Const0(it), Else(it, f)).apply.asInstanceOfSeqIterator
    type apply[it <: Iterator, f <: Function1] =
        `if`[it#isEnd, Const0[it], Else[it, f]]#apply#asInstanceOfSeqIterator

    case class Else[it <: Iterator, f <: Function1](it: it, f: f) extends Function0 {
        type self = Else[it, f]
        override  def apply: apply = `if`(f.apply(it.deref).asInstanceOfBoolean, ElseThen(it, f), Const0(it))
        override type apply        = `if`[f#apply[it#deref]#asInstanceOfBoolean, ElseThen[it, f], Const0[it]]
    }

    case class ElseThen[it <: Iterator, f <: Function1](it: it, f: f) extends Function0 {
        type self = ElseThen[it, f]
        override  def apply: apply = it.next.advanceWhile(f)
        override type apply        = it#next#advanceWhile[f]
    }
}
