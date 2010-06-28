

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package blend; package list

/*
private[mada] object LastOrElse extends Function2[List, Any, Any] {
    override type apply[l <: List, e <: Any] = l#foldRight_Any[e, step]
    override def apply[l <: List, e <: Any](l: l, e: e): apply[l, e] = l.foldRight_Any(e, step)

    val step = new step
    class step extends Function2[List, Any, Any] {
        override type apply[a <: List, b <: Any] = LastOrElse.apply[b#tail, b#head]
        override def apply[a <: List, b <: Any](a: a, b: b): apply[a, b] = LastOrElse.apply(b.tail, b.head)
    }
}
*/


@specializer
sealed abstract class _LastOrElse[l <: List, a] extends ((l, a) => _LastOrElse.result[l, a])


object _LastOrElse {

    type result[l <: List, a] = l#accept_Any[_vt[a]]

    sealed trait _vt[a] extends Visitor[Any] {
        override type visitNil = a
        override type visitCons[h, t <: List] = t#accept_Any[_vt[h]]
    }

    // Synchronize with _LastOrElse.result algorithm can remove asInstanceOf.

    implicit def _ofNil[a] = new _LastOrElse[Nil, a] {
        override def apply(_l: Nil, _a: a) = _a
    }

    implicit def _ofCons[h, t <: List, a](implicit _lastOrElse: _LastOrElse[t, h]) = new _LastOrElse[Cons[h, t], a] {
        override def apply(_l: Cons[h, t], unused: a) = _lastOrElse(_l.tail, _l.head)
    }

}
