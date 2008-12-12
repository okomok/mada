

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object PointerOrdered extends PointerOrdered; trait PointerOrdered {
    implicit def madaPointerToOrdered[A](p: Pointer[A]): Ordered[Pointer[A]] = new Ordered[Pointer[A]] with Proxy {
        Assert("requires RandomAccessPointer", p.traversal <:< Traversal.RandomAccess)
        override val self = p
        override def compare(that: Pointer[A]) = (self - that).toInt
    }
}
