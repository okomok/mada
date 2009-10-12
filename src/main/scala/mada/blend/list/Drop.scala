

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Drop[l <: List, n <: meta.Nat] extends (l => Drop.result[l, n])


object Drop {

    type result[l <: List, n <: meta.Nat] = n#accept[vt[l]]

    sealed abstract class vt[l <: List] extends meta.nat.Visitor {
        override type Result = List
        override type visitZero = l
        override type visitSucc[n <: meta.Nat] = n#accept[vt[l#tail]]
    }

    implicit def ofZero[l <: List] = new Drop[l, meta.Zero] {
        override def apply(_l: l) = _l
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], meta.Succ[n]] {
        override def apply(_l: Cons[h, t]) = _drop(_l.tail)
    }

}
