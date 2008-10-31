
package mada.range



object Outdirect extends OutdirectFunction {
    def apply[X](r: Range[X]): Range[Pointer[X]] = new OutdirectPointer[X](r.begin) <=< new OutdirectPointer[X](r.end)
    override def fromRange[X] = apply[X](_)
}

class OutdirectPointer[E](private var p: Pointer[E]) extends PointerAdapter[E, Pointer[E], OutdirectPointer[E]](p) {
    override def _read = p
    override def _write(e: Pointer[E]) = { throw NotWritable(this) }
    override def _clone = new OutdirectPointer(p.clone)
}
