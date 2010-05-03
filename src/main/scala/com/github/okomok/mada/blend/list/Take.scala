

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list


@specializer
sealed abstract class Take[l <: List, n <: meta.Nat] extends (l => Take.result[l, n])


object Take {

    type result[l <: List, n <: meta.Nat] = n#accept_blendList[vt[l]]

    sealed trait vt[l <: List] extends meta.nat.Visitor[List] {
        override type visitZero = Nil
        override type visitSucc[n <: meta.Nat] = Cons[l#head, n#accept_blendList[vt[l#tail]]]
    }

    implicit def ofZero[l <: List] = new Take[l, meta.Zero] {
        override def apply(_l: l) = Nil
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat](implicit _take: Take[t, n]) = new Take[Cons[h, t], meta.Succ[n]] {
        override def apply(_l: Cons[h, t]) = Cons(_l.head, _take(_l.tail))
    }

}
