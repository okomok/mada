

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


@specializer
trait LastOrElse[l <: List, a] extends ((l, a) => LastOrElse.result[l, a])


object LastOrElse {

    type result[l <: List, a] = l#accept[vt[a]]

    sealed trait vt[a] extends Visitor {
        override type Result = Any
        override type visitNil = a
        override type visitCons[h, t <: List] = t#accept[vt[h]]
    }

    // Synchronizing with LastOrElse.result algorithm can remove asInstanceOf.

    implicit def ofNil[a] = new LastOrElse[Nil, a] {
        override def apply(_l: Nil, _a: a) = _a
    }

    implicit def ofCons[h, t <: List, a](implicit _last: LastOrElse[t, h]) = new LastOrElse[Cons[h, t], a] {
        override def apply(_l: Cons[h, t], unused: a) = _last(_l.tail, _l.head)
    }

}
