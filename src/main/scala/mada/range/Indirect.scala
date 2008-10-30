
package mada.range


class Indirect[E](private val p: Pointer[Pointer[E]], private val q: Pointer[Pointer[E]]) extends Range[E] {
    override def _begin = new IndirectPointer(p)
    override def _end = new IndirectPointer(q)
}


class IndirectPointer[E](private var p: Pointer[Pointer[E]]) extends PointerAdapter[Pointer[E], E, IndirectPointer[E]](p) {
    override def _read = p.read.read
    override def _write(e: E) = p.read.write(e)
    override def _clone = new IndirectPointer[E](p.clone)
}
