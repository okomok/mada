

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list


@specializer
sealed abstract class Nth[l <: List, n <: meta.Nat] extends (l => Nth.result[l, n])


object Nth {

    type result[l <: List, n <: meta.Nat] = n#accept_Any[_vt[l]]

    sealed trait _vt[l <: List] extends meta.nat.Visitor[Any] {
        override type visitZero = l#head
        override type visitSucc[n <: meta.Nat] = n#accept_Any[_vt[l#tail]]
    }

    implicit def _ofZero[h, t <: List] = new Nth[Cons[h, t], meta.Zero] {
        override def apply(_l: Cons[h, t]) = _l.head
    }

    implicit def _ofSucc[h, t <: List, n <: meta.Nat](implicit _nth: Nth[t, n]) = new Nth[Cons[h, t], meta.Succ[n]] {
        override def apply(_l: Cons[h, t]) = _nth(_l.tail)
    }

}
