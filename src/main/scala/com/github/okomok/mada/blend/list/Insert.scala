

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list


@specializer
sealed abstract class Insert[l <: List, n <: meta.Nat, r <: List] extends ((l, r) => Insert.result[l, n, r])


object Insert {

    type result[l <: List, n <: meta.Nat, r <: List] = n#accept_blendList[_vt[l, r]]

    sealed trait _vt[l <: List, r <: List] extends meta.nat.Visitor[List] {
        override type visitZero = _Prepend.result[l, r]
        override type visitSucc[n <: meta.Nat] = Cons[l#head, n#accept_blendList[_vt[l#tail, r]]]
    }

    implicit def _ofZero[l <: List, r <: List](implicit _prepend: _Prepend[l, r]) = new Insert[l, meta.Zero, r] {
        override def apply(_l: l, _r: r) = _prepend(_l, _r)
    }

    implicit def _ofSucc[h, t <: List, n <: meta.Nat, r <: List](implicit _insert: Insert[t, n, r]) = new Insert[Cons[h, t], meta.Succ[n], r] {
        override def apply(_l: Cons[h, t], _r: r) = Cons(_l.head, _insert(_l.tail, _r))
    }

}
