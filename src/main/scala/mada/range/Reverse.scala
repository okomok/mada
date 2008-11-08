
package mada.range


object Reverse {
    def apply[A](base: Range[A]): Range[A] = new ReverseRange(base)
}

class ReverseRange[A](val base: Range[A]) extends Range[A] {
    override val _begin = new ReversePointer(base.end)
    override val _end = new ReversePointer(base.begin)

    override def reverse = base
}

class ReversePointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReversePointer[A]] {
    override def _increment { base--/ }
    override def _clone = new ReversePointer(base.clone)
    override def _decrement { base++/ }
    override def _offset(d: Long) { base -= d }
    override def _difference(that: ReversePointer[A]) = that.base - base
}
