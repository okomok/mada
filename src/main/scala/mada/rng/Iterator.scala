
package mada.rng


object IteratorConversion extends IteratorConversion

trait IteratorConversion {
    implicit def madaRngFromIterator[A](from: Iterator[A]) = FromIterator(from)
    implicit def madaRngToIterator[A](from: Rng[A]) = from.toIterator
}


object FromIterator {
    def apply[A](it: Iterator[A]): Rng[A] = new IteratorRng(it)
}

class IteratorRng[A](val iterator: Iterator[A])
        extends PointerRng[A](
            new IteratorPointer(iterator, if (iterator.hasNext) Some(iterator.next) else None),
            new IteratorPointer(iterator, None)) {
    override def toIterator = iterator
}

class IteratorPointer[A](it: Iterator[A], private var e: Option[A])
        extends PointerFacade[A, IteratorPointer[A]] {
    override def _read = e.get
    override def _traversal = SinglePassTraversal
    override def _equals(that: IteratorPointer[A]) = e.isEmpty == that.e.isEmpty
    override def _increment = { if (it.hasNext) e = Some(it.next) else e = None; }
}


object ToIterator {
    def apply[A](r: Rng[A]): Iterator[A] = new RngIterator(r)
}

class RngIterator[A](r: Rng[A]) extends Iterator[A] {
    private val p = r.begin
    private val q = r.end
    override def hasNext = p != q
    override def next = { p++/; p.read }
}
