
package mada.rng


object Outdirect {
    def apply[A](r: Rng[A]): Rng[Pointer[A]] = new OutdirectRng(r)
}

case class OutdirectRng[A](base: Rng[A]) extends Rng[Pointer[A]] {
    override val _begin = new OutdirectPointer(base.begin)
    override val _end = new OutdirectPointer(base.end)
}

class OutdirectPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override def _read = base
    override def _write(e: Pointer[A]) { throw new ErrorNotWritable(this) }
    override def _clone = new OutdirectPointer(base.clone)
}
