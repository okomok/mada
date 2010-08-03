

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


// really forced?

final class Force[xs <: Seq](xs: xs) extends TrivialForwarder {
    type self = Force[xs]

    override protected lazy val delegate: delegate =
        `if`(xs.isEmpty, Const0(xs), new Else).apply.asInstanceOfSeq
    override protected type delegate =
        `if`[xs#isEmpty, Const0[xs],     Else]#apply#asInstanceOfSeq

    class Else extends Function0 {
        type self = Else
        override  def apply: apply = new AddFirst(new Force(xs.tail), xs.head)
        override type apply        =     AddFirst[    Force[xs#tail], xs#head]
    }
}
