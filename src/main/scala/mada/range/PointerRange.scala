
package mada.range


class PointerRange[A](override val _begin: Pointer[A], override val _end: Pointer[A]) extends Range[A] {
    def this(r: Range[A]) = this(r.begin, r.end)
    override def clone: PointerRange[A] = new PointerRange(begin.clone, end.clone)
    final def equalsSubRange(that: PointerRange[A]): Boolean = (begin == that.begin) && (end == that.end)
}
