
package mada.rng


object PointerOrdered extends PointerOrdered; trait PointerOrdered {
    implicit def madaPointerToOrdered[A](p: Pointer[A]): Ordered[Pointer[A]] = new Ordered[Pointer[A]] with Proxy {
        Assert("requires RandomAccessPointer", p.traversal <:< RandomAccessTraversal)
        override val self = p
        override def compare(that: Pointer[A]) = (self - that).toInt
    }
}
