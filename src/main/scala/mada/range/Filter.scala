
package mada.range


object Filter {
    def apply[A](base: Range[A], function: A => Boolean): Range[A] = new FilterRange(base, function)
}

class FilterRange[A](val base: Range[A], val function: A => Boolean)
        extends {
            val q = base.end
            val q_ = if (q.traversal conformsTo BidirectionalTraversal) q.clone else q
        } with PointerRange[A](new FilterPointer(base.begin, q, function), new FilterPointer(q_, q, function)) {
    override def filter(f: A => Boolean) = base.filter({(e: A) => function(e) && f(e)})
}

class FilterPointer[A](override val _base: Pointer[A], q: Pointer[A], val function: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]] {
    satisfy
    override def _traversal = base.traversal min BidirectionalTraversal
    override def _increment = { base++/; satisfy; }
    override def _clone = new FilterPointer(base.clone, q, function)
    override def _decrement = { while (!function(*(base--/))) { } }
    final private def satisfy = { while (base != q && !function(*(base))) { base++/ } }
}
