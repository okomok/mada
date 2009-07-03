

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


// If metamethod were feasible, it would return `meta.error`.


@specializer
trait FindType[l <: List, a] extends (l => a)


object FindType {

    implicit def ofCons[h, t <: List, a](implicit _findType: FindType[t, a]) = new FindType[Cons[h, t], a] {
        override def apply(_l: Cons[h, t]) = _findType(_l.tail)
    }

    implicit def ofConsMatch[t <: List, a] = new FindType[Cons[a, t], a] {
        override def apply(_l: Cons[a, t]) = _l.head
    }

}
