
package mada.range


object Outdirect {
    def apply[A](r: Range[A]): Range[Pointer[A]] = new OutdirectRange(r)
}

case class OutdirectRange[A](base: Range[A]) extends Range[Pointer[A]] {
    override val _begin = new OutdirectPointer(base.begin)
    override val _end = new OutdirectPointer(base.end)
}

class OutdirectPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override def _read = base
    override def _write(e: Pointer[A]) { throw new ErrorNotWritable(this) }
    override def _clone = new OutdirectPointer(base.clone)
}
