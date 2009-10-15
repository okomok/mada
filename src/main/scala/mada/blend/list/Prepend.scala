

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Prepend[r <: List, l <: List] extends ((r, l) => Prepend.result[r, l])


object Prepend {

    type result[r <: List, l <: List] = l#accept_List[vt[r]]

    sealed abstract class vt[r <: List] extends Visitor[List] {
        override type visitNil = r
        override type visitCons[h, t <: List] = Cons[h, t#accept_List[vt[r]]]
    }

    implicit def ofNil[r <: List] = new Prepend[r, Nil] {
        override def apply(_r: r, _l: Nil) = _r
    }

    implicit def ofCons[r <: List, h, t <: List](implicit _prepend: Prepend[r, t]) = new Prepend[r, Cons[h, t]] {
        override def apply(_r: r, _l: Cons[h, t]) = Cons(_l.head, _prepend(_r, _l.tail))
    }

}
