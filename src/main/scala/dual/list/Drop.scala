

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class Drop[xs <: List, n <: Nat](xs: xs, n: n) extends TrivialForwarder {
    type self = Drop[xs, n]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty  || n.isZero,  const0(xs), new Else).apply.asInstanceOfList.asInstanceOf[delegate]
    override protected type delegate =
        `if`[xs#isEmpty# ||[n#isZero], const0[xs],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new Drop(xs.tail, n.decrement)
        override type apply        =     Drop[xs#tail, n#decrement]
    }
}


final class DropWhile[xs <: List, f <: Function1](xs: xs, f: f) extends TrivialForwarder {
    type self = DropWhile[xs, f]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, const0(xs), new Else).apply.asInstanceOfList
    override protected type delegate =
        `if`[xs#isEmpty, const0[xs],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = `if`(f.apply(xs.head).asInstanceOfBoolean, new ElseThen, const0(xs)).apply.asInstanceOf[apply]
        override type apply        = `if`[f#apply[xs#head]#asInstanceOfBoolean,     ElseThen, const0[xs]]#apply
    }

    class ElseThen extends Function0 {
        type self = ElseThen
        override  def apply: apply = new DropWhile(xs.tail, f)
        override type apply        =     DropWhile[xs#tail, f]
    }
}
