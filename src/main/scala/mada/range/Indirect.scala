
package mada.range


object Indirect {
    def apply[A](r: Range[Pointer[A]]): Range[A] =
        new IndirectPointer(r.begin) <=< new IndirectPointer(r.end)
}

class IndirectPointer[A](private var p: Pointer[Pointer[A]])
        extends PointerAdapter[Pointer[A], A, IndirectPointer[A]](p) {
    override def _read = *(*(base))
    override def _write(e: A) = *(*(base)) = e
    override def _clone = new IndirectPointer[A](base.clone)
}
