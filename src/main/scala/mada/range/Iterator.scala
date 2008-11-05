
package mada.range


object FromIterator {
    def apply[A](it: Iterator[A]): Range[A] = new IteratorRange(it)
}

class IteratorRange[A](val iterator: Iterator[A])
        extends PointerRange[A](
            new IteratorPointer(iterator, if (iterator.hasNext) Some(iterator.next) else None),
            new IteratorPointer(iterator, None)) {
}

class IteratorPointer[A](it: Iterator[A], private var e: Option[A])
        extends PointerFacade[A, IteratorPointer[A]] {
    override def _read = e.get
    override def _traversal = SinglePassTraversal
    override def _equals(that: IteratorPointer[A]) = e.isEmpty == that.e.isEmpty
    override def _increment = { if (it.hasNext) e = Some(it.next) else e = None; }
}


object ToIterator {
    def apply[A](r: Range[A]): Iterator[A] = new RangeIterator(r)
}

class RangeIterator[A](r: Range[A]) extends Iterator[A] {
    private val p = r.begin
    private val q = r.end
    override def hasNext = p != q
    override def next = { p++/; p.read }
}
