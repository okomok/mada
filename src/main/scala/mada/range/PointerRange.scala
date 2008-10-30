
package mada.range


class PointerRange[E](private val p: Pointer[E], private val q: Pointer[E]) extends Range[E] {
    override def _begin = p
    override def _end = q

    override def clone: PointerRange[E] = new PointerRange(p.clone, q.clone)
    override final def equals(that: Any): Boolean = that match {
        case that: PointerRange[E] => (p == that.p) && (q == that.q) // This seems right.
        case _ => false
    }
}
