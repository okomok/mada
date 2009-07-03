

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend.list


// lastOption might be better, but compiler can't find specializer for scala.None.type.


@specializer
trait Last[l <: List, z] extends ((l, z) => Last.result[l, z])


object Last {

    type result[l <: List, z] = l#accept[vt[z]]

    sealed trait vt[z] extends Visitor {
        override type Result = Any
        override type visitNil = z
        override type visitCons[h, t <: List] = t#accept[vt[h]]
    }

    // Synchronizing with Last.result algorithm can remove asInstanceOf.

    implicit def ofNil[z] = new Last[Nil, z] {
        override def apply(_l: Nil, _z: z) = _z
    }

    implicit def ofCons[h, t <: List, z](implicit _last: Last[t, h]) = new Last[Cons[h, t], z] {
        override def apply(_l: Cons[h, t], unused: z) = _last(_l.tail, _l.head)
    }

}
