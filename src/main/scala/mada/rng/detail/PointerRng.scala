
package mada.rng.detail


class PointerRng[A](p: Pointer[A], q: Pointer[A]) extends Rng[A] {
    override def _begin = copyForward(p)
    override def _end = copyForward(q)
    private def copyForward(i: Pointer[A]) = if (i.traversal <:< Traversal.Forward) i.copy else i
}
