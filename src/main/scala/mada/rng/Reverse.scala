
package mada.rng


object Reverse {
    def apply[A](base: Rng[A]): Rng[A] = new ReverseRng(base)
}

class ReverseRng[A](val base: Rng[A]) extends Rng[A] {
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
