

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class LastOrElse[l <: List, a] extends ((l, a) => LastOrElse.result[l, a])


object LastOrElse {

    type result[l <: List, a] = l#accept_Any[vt[a]]

    sealed trait vt[a] extends Visitor[Any] {
        override type visitNil = a
        override type visitCons[h, t <: List] = t#accept_Any[vt[h]]
    }

    // Synchronize with LastOrElse.result algorithm can remove asInstanceOf.

    implicit def ofNil[a] = new LastOrElse[Nil, a] {
        override def apply(_l: Nil, _a: a) = _a
    }

    implicit def ofCons[h, t <: List, a](implicit _lastOrElse: LastOrElse[t, h]) = new LastOrElse[Cons[h, t], a] {
        override def apply(_l: Cons[h, t], unused: a) = _lastOrElse(_l.tail, _l.head)
    }

}
