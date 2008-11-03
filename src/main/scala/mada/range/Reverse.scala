
package mada.range


object Reverse {
    def apply[A](base: Range[A]): Range[A] = new ReverseRange(base)
}

class ReverseRange[A](val base: Range[A])
        extends PointerRange[A](new ReversePointer(base.end), new ReversePointer(base.begin)) {
    override def reverse = base
}

class ReversePointer[A](private val p: Pointer[A]) extends PointerAdapter[A, A, ReversePointer[A]](p) {
    override def _increment = base--/
    override def _clone = new ReversePointer(base.clone)
    override def _decrement = base++/
    override def _offset(d: Long) = base -= d
    override def _difference(that: ReversePointer[A]) = that.base - base
}
