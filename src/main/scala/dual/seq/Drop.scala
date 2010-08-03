

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class Drop[xs <: Seq, n <: Nat](xs: xs, n: n) extends TrivialForwarder {
    type self = Drop[xs, n]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty || n.isZero,  Const0(xs), new Else).apply.asInstanceOfSeqSeq
    override protected type delegate =
        `if`[xs#isEmpty ||[n#isZero], Const0[xs],     Else]#apply#asInstanceOfSeqSeq

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Drop(xs.tail, n.decrement).apply
        override type apply        =     Drop[xs#tail, n#decrement]#apply
    }
}


final class DropWhile[xs <: Seq, f <: Function1](xs: xs, f: f) extends TrivialForwarder {
    type self = DropWhile[xs, f]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, Const0(xs), new Else).apply.asInstanceOfSeqSeq
    override protected type delegate =
        `if`[xs#isEmpty, Const0[xs],     Else]#apply#asInstanceOfSeqSeq

    private class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(f.apply(xs.head).asInstanceOfBoolean, new ElseThen, Const0(xs))
        override type apply        = `if`[f#apply[xs#head]#asInstanceOfBoolean,     ElseThen, Const0[xs]]
    }

    private class ElseThen extends Function0 {
        type self = ElseThen
        override  def apply: apply = new DropWhile(xs.tail, f).apply
        override type apply        =     DropWhile[xs#tail, f]#apply
    }
}
