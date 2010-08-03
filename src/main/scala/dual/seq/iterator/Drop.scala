

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq; package iterator


final class Drop[it <: Iterator, n <: Nat](it: it, n: n) extends Forwarder {
    type self = Drop[it, n]

    override protected lazy val delegate: delegate =
        `if`(it.isEnd || n.isZero,  Const0(it), new Else).apply.asInstanceOfSeqIterator
    override protected type delegate =
        `if`[it#isEnd ||[n#isZero], Const0[it],     Else]#apply#asInstanceOfSeqIterator

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Drop(it.next, n.decrement).apply
        override type apply        =     Drop[it#next, n#decrement]#apply // afraid of ETI.
    }
}


final class DropWhile[it <: Iterator, f <: Function1](it: it, f: f) extends Forwarder {
    type self = DropWhile[it, f]

    override protected lazy val delegate: delegate =
        `if`(it.isEnd, Const0(it), new Else).apply.asInstanceOfSeqIterator
    override protected type delegate =
        `if`[it#isEnd, Const0[it],     Else]#apply#asInstanceOfSeqIterator

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(f.apply(it.deref).asInstanceOfBoolean, new ElseThen, Const0(it))
        override type apply        = `if`[f#apply[it#deref]#asInstanceOfBoolean,     ElseThen, Const0[it]]
    }

    private class ElseThen extends Function0 {
        type self = ElseThen
        override  def apply: apply = new DropWhile(it.next, f).apply
        override type apply        =     DropWhile[it#next, f]#apply
    }
}
