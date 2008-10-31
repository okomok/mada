
package mada.range


case class Filter[E](f: E => Boolean) extends RangeTransformation {
    def apply[X <: E](r: Range[X]): Range[X] = {
        val q = r.end
        new FilterPointer[X](r.begin, q, f) <=< new FilterPointer[X](r.end, q, f)
    }
    override def fromRange[X] = Filter[X](f.asInstanceOf[X => Boolean]).apply[X](_)
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
