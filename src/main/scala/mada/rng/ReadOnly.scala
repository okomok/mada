
package mada.rng


object ReadOnly {
    def apply[A](base: Rng[A]): Rng[A] = new ReadOnlyRng(base)
}

class ReadOnlyRng[A](val base: Rng[A]) extends Rng[A] {
    override val _begin = new ReadOnlyPointer(base.begin)
    override val _end = new ReadOnlyPointer(base.end)

    override def readOnly = this
}

class ReadOnlyPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReadOnlyPointer[A]] {
    override def _write(e: A) { throw new ErrorNotWritable(this) }
    override def _clone = new ReadOnlyPointer(base.clone)
}
