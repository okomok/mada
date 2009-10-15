

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Slice[l <: List, n <: meta.Nat, m <: meta.Nat] extends (l => Slice.result[l, n, m])


object Slice {

    type result[l <: List, n <: meta.Nat, m <: meta.Nat] = n#acceptBlendList[vt[l, m]]

    sealed abstract class vt[l <: List, m <: meta.Nat] extends meta.nat.Visitor[List] {
        override type visitZero = Take.result[l, m]
        override type visitSucc[n <: meta.Nat] = n#acceptBlendList[vt[l#tail, m#decrement]]
    }

    implicit def ofZero[l <: List, m <: meta.Nat](implicit _take: Take[l, m]) = new Slice[l, meta.Zero, m] {
        override def apply(_l: l) = _take(_l)
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat, m <: meta.Nat](implicit _slice: Slice[t, n, m]) = new Slice[Cons[h, t], meta.Succ[n], meta.Succ[m]] {
        override def apply(_l: Cons[h, t]) = _slice(_l.tail)
    }

}
