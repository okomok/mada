
package mada.range


class Outdirect[E](private val p: Pointer[E], private val q: Pointer[E]) extends Range[Pointer[E]] {
    override def _begin = new OutdirectPointer(p)
    override def _end = new OutdirectPointer(q)
}


class OutdirectPointer[E](private var p: Pointer[E]) extends PointerAdapter[E, Pointer[E], OutdirectPointer[E]](p) {
    override def _read = p
    override def _write(e: Pointer[E]) = { throw NotWritable(this) }
    override def _clone = new OutdirectPointer(p.clone)
}
