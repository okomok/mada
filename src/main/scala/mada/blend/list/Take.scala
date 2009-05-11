

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


@specializer
trait Take[l <: List, n <: meta.Nat] extends (l => Take.result[l, n])


object Take {

    type result[l <: List, n <: meta.Nat] = n#accept[vt[l]]

    sealed trait vt[l <: List] extends meta.nat.Visitor {
        override type Result = List
        override type visitZero = Nil
        override type visitSucc[n <: meta.Nat] = Cons[l#head, n#accept[vt[l#tail]]]
    }

    implicit def ofZero[l <: List] = new Take[l, meta.Zero] {
        override def apply(_l: l) = Nil
    }

    implicit def ofSucc[h, t <: List, n <: meta.Nat](implicit _take: Take[t, n]) = new Take[Cons[h, t], meta.Succ[n]] {
        override def apply(_l: Cons[h, t]) = Cons(_l.head, _take(_l.tail))
    }

}
