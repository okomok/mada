
package mada.range


class IteratorRange[E](val base: Iterator[E]) extends Range[E] {
    override def _begin = new IteratorPointer(base, if (base.hasNext) Some(base.next) else None)
    override def _end = new IteratorPointer(base, None)
}

class IteratorPointer[E](private val it: Iterator[E], private var e: Option[E])
    extends PointerFacade[E, IteratorPointer[E]] {
    override def _read = e.get
    override def _traversalTag = SinglePassTraversalTag()
    override def _equals(that: IteratorPointer[E]) = e.isEmpty == that.e.isEmpty
    override def _increment = { if (it.hasNext) e = Some(it.next) else e = None; }
}
