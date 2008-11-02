
package mada.range


object Filter {
    def apply[A](r: Range[A], g: A => Boolean): Range[A] = new FilterRange(r, g, r.end)
}

class FilterRange[A](private val r: Range[A], private val g: A => Boolean, private val q: Pointer[A])
        extends PointerRange[A](
            new FilterPointer(r.begin, q, g),
            new FilterPointer(q.cloneIn(BidirectionalTraversal()), q, g)) {
    override def filter(f: A => Boolean) = r.filter({(a: A) => g(a) && f(a)})
}

class FilterPointer[A](private val p: Pointer[A], private val q: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]](p) {
    satisfy
    override def _traversal = p.traversal min BidirectionalTraversal()
    override def _increment = { p++/; satisfy; }
    override def _clone = new FilterPointer(p.clone, q.clone, predicate)
    override def _decrement = { while (!predicate((p--/).read)) { } }
    final private def satisfy = { while (p != q && !predicate(p.read)) { p++/; } }
}
