

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list


@specializer
sealed abstract class ElementOf[l <: List, a] extends (l => ElementOf.result[l, a])


object ElementOf {

    type result[l <: List, a] = a

    implicit def ofCons[h, t <: List, a](implicit _elementOf: ElementOf[t, a]) = new ElementOf[Cons[h, t], a] {
        override def apply(_l: Cons[h, t]) = _elementOf(_l.tail)
    }

    implicit def ofConsMatch[t <: List, a] = new ElementOf[Cons[a, t], a] {
        override def apply(_l: Cons[a, t]) = _l.head
    }

}
