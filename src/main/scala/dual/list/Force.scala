

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// really forced?

final class Force[xs <: List](xs: xs) extends Function0 {
    type self = Force[xs]

    override  def apply: apply =
        `if`(xs.isEmpty, Const0(Nil), new Else).apply.asInstanceOfList
    override type apply =
        `if`[xs#isEmpty, Const0[Nil],     Else]#apply#asInstanceOfList

    class Else extends Function0 {
        type self = Else
        private lazy val r: r = new Force(xs.tail).apply.asInstanceOf[r]
        private type r        =     Force[xs#tail]#apply
        override  def apply: apply = new Cons(xs.head, r)
        override type apply        =     Cons[xs#head, r]
    }
}
