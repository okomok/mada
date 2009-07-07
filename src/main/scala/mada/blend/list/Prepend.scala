

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


@specializer
trait Prepend[r <: List, l <: List] extends ((r, l) => Prepend.result[r, l])


object Prepend {

    type result[r <: List, l <: List] = l#accept[vt[r]]

    sealed trait vt[r <: List] extends Visitor {
        override type Result = List
        override type visitNil = r
        override type visitCons[h, t <: List] = Cons[h, t#accept[vt[r]]]
    }

    implicit def ofNil[r <: List] = new Prepend[r, Nil] {
        override def apply(_r: r, _l: Nil) = _r
    }

    implicit def ofCons[r <: List, h, t <: List](implicit _prepend: Prepend[r, t]) = new Prepend[r, Cons[h, t]] {
        override def apply(_r: r, _l: Cons[h, t]) = Cons(_l.head, _prepend(_r, _l.tail))
    }

}
