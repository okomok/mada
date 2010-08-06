

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


final class ReverseAppend[xs <: List, ys <: List](xs: xs, ys: ys) extends TrivialForwarder {
    type self = ReverseAppend[xs, ys]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, const0(ys), new Else).apply.asInstanceOfList
    override protected type delegate =
        `if`[xs#isEmpty, const0[ys],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new ReverseAppend(xs.tail, new Cons(xs.head, ys))
        override type apply        =     ReverseAppend[xs#tail,     Cons[xs#head, ys]]
    }
}
