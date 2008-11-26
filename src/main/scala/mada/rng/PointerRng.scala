
package mada.rng


class PointerRng[A](p: Pointer[A], q: Pointer[A]) extends Rng[A] {
    def this(r: Rng[A]) = this(r.begin, r.end)

    override def _begin = copyForward(p)
    override def _end = copyForward(q)

    private def copyForward(i: Pointer[A]) = if (i.traversal <:< ForwardTraversal) i.copy else i
}
