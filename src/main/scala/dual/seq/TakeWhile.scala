

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package seq


private[mada] final class TakeWhile {
     def apply[xs <: Seq, f <: Function1](xs: xs, f: f): apply[xs, f] =
        xs.fromSuper(`if`(xs.isEmpty, Const0(xs), Else(xs, f)).apply.asInstanceOfSeq)
    type apply[xs <: Seq, f <: Function1] =
        xs#fromSuper[`if`[xs#isEmpty, Const0[xs], Else[xs, f]]#apply#asInstanceOfSeq]

    case class Else[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = Else[xs, f]
        override  def apply: apply =
            `if`(f.apply(xs.head).asInstanceOfBoolean, ElseThen(xs, f), Const0(xs.clear)).apply.asInstanceOf[apply]
        override type apply =
            `if`[f#apply[xs#head]#asInstanceOfBoolean, ElseThen[xs, f], Const0[xs#clear]]#apply
    }

    case class ElseThen[xs <: Seq, f <: Function1](xs: xs, f: f) extends Function0 {
        type self = ElseThen[xs, f]
        override  def apply: apply = xs.tail.takeWhile(f).addFirst(xs.head)
        override type apply        = xs#tail#takeWhile[f]#addFirst[xs#head]
    }
}
