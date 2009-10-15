

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Insert[l <: List, n <: meta.Nat, r <: List] extends ((l, r) => Insert.result[l, n, r])


object Insert {

    type result[l <: List, n <: meta.Nat, r <: List] = n#accept_blendList[vt[l, r]]

    sealed abstract class vt[l <: List, r <: List] extends meta.nat.Visitor[List] {
        override type visitZero = Prepend.result[l, r]
        override type visitSucc[n <: meta.Nat] = Cons[l#head, n#accept_blendList[vt[l#tail, r]]]
    }

    implicit def ofZero[l <: List, r <: List](implicit _prepend: Prepend[l, r]) = new Insert[l, meta.Zero, r] {
        override def apply(_l: l, _r: r) = _prepend(_l, _r)
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat, r <: List](implicit _insert: Insert[t, n, r]) = new Insert[Cons[h, t], meta.Succ[n], r] {
        override def apply(_l: Cons[h, t], _r: r) = Cons(_l.head, _insert(_l.tail, _r))
    }

}
