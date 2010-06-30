

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


@specializer
sealed abstract class ElementOf[xs <: List, e <: Any] extends (xs => ElementOf.result[xs, e])


object ElementOf {

    type result[xs <: List, e] = e

    implicit def _ofCons[a, as <: List, e](implicit _elementOf: ElementOf[as, e]) = new ElementOf[Cons[a, as], e] {
        override def apply(xs: Cons[a, as]) = _elementOf(xs.tail)
    }

    implicit def _ofConsMatch[as <: List, e] = new ElementOf[Cons[e, as], e] {
        override def apply(xs: Cons[e, as]) = xs.head
    }

}
