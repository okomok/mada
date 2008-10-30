
package mada.range


case class Filter[E](f: E => Boolean) extends (Range[E] => Range[E]) {
    override final def apply(r: Range[E]) = new Range[E] {
        private val q = r.end
        override def _begin = new FilterPointer(r.begin, q, f)
        override def _end = new FilterPointer(r.end, q, f)
    }
}


class FilterPointer[E](private val p: Pointer[E], private val q: Pointer[E], val predicate: E => Boolean)
    extends PointerAdapter[E, E, FilterPointer[E]](p) {
    satisfy
    override def _traversalTag = p.traversalTag min BidirectionalTraversalTag()
    override def _increment = { p++/; satisfy; }
    override def _clone = new FilterPointer(p.clone, q.clone, predicate)
    override def _decrement = { while (!predicate((p--/).read)) { } }
    final private def satisfy = { while (p != q && !predicate(p.read)) { p++/; } }
}
