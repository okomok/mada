

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


private[mada] final class Count {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] =
        xs.foldRight(nat.peano._0, Step(xs, f)).asInstanceOfNat
    type apply[xs <: List, f <: Function1] =
        xs#foldRight[nat.peano._0, Step[xs, f]]#asInstanceOfNat

    case class Step[xs <: List, f <: Function1](xs: xs, f: f) extends Function2 {
        type self = Step[xs, f]
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] =
            `if`(f.apply(a).asInstanceOfBoolean, Const0(b.asInstanceOfNat.increment), Const0(b)).apply.asInstanceOf[apply[a, b]]
        override type apply[a <: Any, b <: Any] =
            `if`[f#apply[a]#asInstanceOfBoolean, Const0[b#asInstanceOfNat#increment], Const0[b]]#apply
    }
}
