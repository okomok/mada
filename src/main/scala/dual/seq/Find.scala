

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class Find {
     def apply[xs <: Seq, f <: Function1](xs: xs, f: f): apply[xs, f] =
        `if`(xs.isEmpty, Const0(None), Else(xs, f)).apply.asInstanceOfOption
    type apply[xs <: Seq, f <: Function1] =
        `if`[xs#isEmpty, Const0[None], Else[xs, f]]#apply#asInstanceOfOption

    case class Else[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply =
            `if`(f.apply(xs.head).asInstanceOfBoolean, Const0(Some(xs.head)), ElseElse(xs, f)).apply.asInstanceOf[apply]
        override type apply =
            `if`[f#apply[xs#head]#asInstanceOfBoolean, Const0[Some[xs#head]], ElseElse[xs, f]]#apply
    }

    case class ElseElse[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = ElseElse[xs, f]
        override  def apply: apply = xs.tail.find(f)
        override type apply        = xs#tail#find[f]
    }
}
