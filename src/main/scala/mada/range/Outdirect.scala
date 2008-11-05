
package mada.range


object Outdirect {
    def apply[A](r: Range[A]): Range[Pointer[A]] =
        new OutdirectPointer(r.begin) <=< new OutdirectPointer(r.end)
}

class OutdirectPointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]] {
    override def _read = base
    override def _write(e: Pointer[A]) = { throw new ErrorNotWritable(this) }
    override def _clone = new OutdirectPointer(base.clone)
}
