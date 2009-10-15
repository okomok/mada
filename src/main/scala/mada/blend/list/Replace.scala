

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Replace[l <: List, n <: meta.Nat, a] extends ((l, a) => Replace.result[l, n, a])


object Replace {

    type result[l <: List, n <: meta.Nat, a] = n#acceptBlendList[vt[l, a]]

    sealed abstract class vt[l <: List, a] extends meta.nat.Visitor[List] {
        override type visitZero = Cons[a, l#tail]
        override type visitSucc[n <: meta.Nat] = Cons[l#head, n#acceptBlendList[vt[l#tail, a]]]
    }

    implicit def ofZero[h, t <: List, a] = new Replace[Cons[h, t], meta.Zero, a] {
        override def apply(_l: Cons[h, t], _a: a) = Cons(_a, _l.tail)
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat, a](implicit _replace: Replace[t, n, a]) = new Replace[Cons[h, t], meta.Succ[n], a] {
        override def apply(_l: Cons[h, t], _a: a) = Cons(_l.head, _replace(_l.tail, _a))
    }

}
