
package mada.range


object Outdirect {
    def apply[A](r: Range[A]): Range[Pointer[A]] =
        new OutdirectPointer(r.begin) <=< new OutdirectPointer(r.end)
}

class OutdirectPointer[A](private var p: Pointer[A]) extends PointerAdapter[A, Pointer[A], OutdirectPointer[A]](p) {
    override def _read = base
    override def _write(e: Pointer[A]) = { throw ErrorNotWritable(this) }
    override def _clone = new OutdirectPointer(base.clone)
}
