

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


@specializer
trait FindType[l <: List, k] extends (l => FindType.result[l, k])


object FindType {

    type result[l <: List, k] = k

    implicit def ofCons[h, t <: List, k](implicit _findType: FindType[t, k]) = new FindType[Cons[h, t], k] {
        override def apply(_l: Cons[h, t]) = _findType(_l.tail)
    }

    implicit def ofConsMatch[t <: List, k] = new FindType[Cons[k, t], k] {
        override def apply(_l: Cons[k, t]) = _l.head
    }

}
