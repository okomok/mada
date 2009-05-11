

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


@specializer
trait ReversePrepend[r <: List, l <: List] extends ((r, l) => ReversePrepend.result[r, l])

object ReversePrepend {

    type result[r <: List, l <: List] = l#accept[vt[r]]

    sealed trait vt[r <: List] extends Visitor {
        override type Result = List
        override type visitNil = r
        override type visitCons[h, t <: List] = t#accept[vt[Cons[h, r]]]
    }

    implicit def ofNil[r <: List] = new ReversePrepend[r, Nil] {
        override def apply(_r: r, _l: Nil) = _r
    }

    implicit def ofCons[r <: List, h, t <: List](implicit _reversePrepend: ReversePrepend[Cons[h, r], t]) = new ReversePrepend[r, Cons[h, t]] {
        override def apply(_r: r, _l: Cons[h, t]) = _reversePrepend(Cons(_l.head, _r), _l.tail)
    }

}
