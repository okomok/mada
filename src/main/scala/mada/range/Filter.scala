
package mada.range


object Filter {
    def apply[A](base: Range[A], predicate: A => Boolean): Range[A] = new FilterRange(base, predicate)
}

class FilterRange[A](val base: Range[A], val predicate: A => Boolean) extends Range[A] {
    val q = base.end
    override val _begin = new FilterPointer(base.begin, q, predicate)
    override val _end = new FilterPointer(q.cloneIn(BidirectionalTraversal), q, predicate)

    override def filter(f: A => Boolean) = base.filter({(e: A) => predicate(e) && f(e)})
}

class FilterPointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]] {
    satisfy
    override def _traversal = base.traversal min BidirectionalTraversal
    override def _increment { base++/; satisfy }
    override def _clone = new FilterPointer(base.clone, end, predicate)
    override def _decrement { while (!predicate(*(base--/))) { } }
    private def satisfy { while (base != end && !predicate(*(base))) { base++/ } }
}
