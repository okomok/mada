
package mada.range


object -- {
    def apply[E](p: Pointer[E]) = p--/
    // --(p).read seems sucks
}


class FilterPointer[E](private val p: Pointer[E], private val q: Pointer[E], val predicate: E => Boolean)
extends PointerAdapter[E, E, FilterPointer[E]](p) {
    satisfy
    override def _increment = { p++/; satisfy; }
    override def _clone = new FilterPointer(p.clone, q.clone, predicate)
    override def _decrement = { while (!predicate((p--/).read)) { } }
    final private def satisfy = { while (p != q && !predicate(p.read)) { p++/; } }
}
