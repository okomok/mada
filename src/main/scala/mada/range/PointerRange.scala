
package mada.range


class PointerRange[A](private val p: Pointer[A], private val q: Pointer[A]) extends Range[A] {
    def this(r: Range[A]) = this(r.begin, r.end)

    override def _begin = p
    override def _end = q

    override def clone: PointerRange[A] = new PointerRange(p.clone, q.clone)

    final def eqSubRange(that: PointerRange[A]): Boolean = (p == that.p) && (q == that.q)
}
