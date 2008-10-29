
package mada.range


trait Range[E] {
    protected def _begin: Pointer[E]
    protected def _end: Pointer[E]

    final def begin = _begin
    final def end = _end
    final def traversalTag = begin.traversalTag

    final def toIterator = new Iterator[E] {
        private val first = begin
        override def hasNext = first != end
        override def next() = { first++/; first.read }
    }
}


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
