

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list


@specializer
sealed abstract class _Prepend[r <: List, l <: List] extends ((r, l) => _Prepend.result[r, l])


object _Prepend {

    type result[r <: List, l <: List] = l#accept_List[_vt[r]]

    sealed trait _vt[r <: List] extends Visitor[List] {
        override type visitNil = r
        override type visitCons[h, t <: List] = Cons[h, t#accept_List[_vt[r]]]
    }

    implicit def _ofNil[r <: List] = new _Prepend[r, Nil] {
        override def apply(_r: r, _l: Nil) = _r
    }

    implicit def _ofCons[r <: List, h, t <: List](implicit _prepend: _Prepend[r, t]) = new _Prepend[r, Cons[h, t]] {
        override def apply(_r: r, _l: Cons[h, t]) = Cons(_l.head, _prepend(_r, _l.tail))
    }

}


private[mada] object Prepend extends Function2[List, List, List] {
    override type apply[r <: List, l <: List] = l#foldRight_List[r, step]
    override def apply[r <: List, l <: List](_r: r, _l: l): apply[r, l] = _l.foldRight_List(_r, new step)

    class step extends Function2[Any, List, List] {
        override type apply[a <: Any, b <: List] = Cons[a, b]
        override def apply[a <: Any, b <: List](_a: a, _b: b): apply[a, b] = Cons(_a, _b)
    }
}
