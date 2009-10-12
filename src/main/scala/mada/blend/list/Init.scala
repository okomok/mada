

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


// init == reverse.tail.reverse
// reverse(l) == prependReversed(Nil, l)


@specializer
trait Init[r <: List, l <: List] extends ((r, l) => Init.result[r, l])


object Init {

    type result[r <: List, l <: List] = l#accept[vt[r]]

    sealed trait vt[r <: List] extends Visitor {
        override type Result = List
        override type visitNil = PrependReversed.result[Nil, r#tail]
        override type visitCons[h, t <: List] = t#accept[vt[Cons[h, r]]]
    }

    implicit def ofNil[h, t <: List](implicit _prependReversed: PrependReversed[Nil, t]) = new Init[Cons[h, t], Nil] {
        override def apply(_r: Cons[h, t], _l: Nil) = _prependReversed(Nil, _r.tail)
    }

    implicit def ofCons[r <: List, h, t <: List](implicit _init: Init[Cons[h, r], t]) = new Init[r, Cons[h, t]] {
        override def apply(_r: r, _l: Cons[h, t]) = _init(Cons(_l.head, _r), _l.tail)
    }

}
