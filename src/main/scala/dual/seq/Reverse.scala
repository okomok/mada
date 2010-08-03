

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


final class ReverseAppend[xs <: Seq, ys <: Seq](xs: xs, ys: ys) extends TrivialForwarder {
    type self = ReverseAppend[xs, ys]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, Const0(ys), new Else).apply.asInstanceOfSeq
    override protected type delegate =
        `if`[xs#isEmpty, Const0[ys],     Else]#apply#asInstanceOfSeq

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new ReverseAppend(xs.tail, new AddFirst(ys, xs.head))
        override type apply        =     ReverseAppend[xs#tail,     AddFirst[ys, xs#head]]
    }
}
