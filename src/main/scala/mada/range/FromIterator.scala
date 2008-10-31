
package mada.range


object FromIterator {
    def apply[E](it: Iterator[E]): Range[E] =
        new IteratorPointer(it, if (it.hasNext) Some(it.next) else None) <=<
            new IteratorPointer(it, None)
}

class IteratorPointer[E](private val it: Iterator[E], private var e: Option[E])
        extends PointerFacade[E, IteratorPointer[E]] {
    override def _read = e.get
    override def _traversal = SinglePassTraversal()
    override def _equals(that: IteratorPointer[E]) = e.isEmpty == that.e.isEmpty
    override def _increment = { if (it.hasNext) e = Some(it.next) else e = None; }
}
