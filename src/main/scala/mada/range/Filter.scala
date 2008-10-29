
package mada.range


class FilterPointer[E](private val p: Pointer[E], private val q: Pointer[E], val predicate: E => Boolean)
extends PointerAdapter[E, E, FilterPointer[E]](p) {
    satisfy
    override def _read = p.read
    override def _write(e: E) = p.write(e)
    override def _increment = { p.pre_++; satisfy; }
    override def _clone = new FilterPointer(p.clone, q.clone, predicate)
    override def _decrement = { while (!predicate(p.pre_--.read)) { } }
    final private def satisfy = { while (p != q && !predicate(p.read)) { p.pre_++; } }
}
