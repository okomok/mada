

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class PrependReversed[r <: List, l <: List] extends ((r, l) => PrependReversed.result[r, l])

object PrependReversed {

    type result[r <: List, l <: List] = l#accept[vt[r]]

    sealed abstract class vt[r <: List] extends Visitor {
        override type Result = List
        override type visitNil = r
        override type visitCons[h, t <: List] = t#accept[vt[Cons[h, r]]]
    }

    implicit def ofNil[r <: List] = new PrependReversed[r, Nil] {
        override def apply(_r: r, _l: Nil) = _r
    }

    implicit def ofCons[r <: List, h, t <: List](implicit _prependReversed: PrependReversed[Cons[h, r], t]) = new PrependReversed[r, Cons[h, t]] {
        override def apply(_r: r, _l: Cons[h, t]) = _prependReversed(Cons(_l.head, _r), _l.tail)
    }

}
