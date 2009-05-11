

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


// lastOption might be better, but compiler can't find specializer for scala.None.type.


@specializer
trait Last[l <: List, e] extends ((l, e) => Last.result[l, e])


object Last {

    type result[l <: List, e] = l#accept[vt[e]]

    sealed trait vt[e] extends Visitor {
        override type Result = Any
        override type visitNil = e
        override type visitCons[h, t <: List] = t#accept[vt[h]]
    }

    // Synchronizing with Last.result algorithm can remove asInstanceOf.

    implicit def ofNil[e] = new Last[Nil, e] {
        override def apply(_l: Nil, _e: e) = _e
    }

    implicit def ofCons[h, t <: List, e](implicit _last: Last[t, h]) = new Last[Cons[h, t], e] {
        override def apply(_l: Cons[h, t], unused: e) = _last(_l.tail, _l.head)
    }

}
