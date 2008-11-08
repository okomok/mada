
package mada.range


// Take

object Take {
    def apply[A](base: Range[A], count: Long): Range[A] = new TakeRange(base, count)
}

class TakeRange[A](val base: Range[A], val count: Long) extends Range[A] {
    private val p = base.begin
    private val q = base.end
    override val _begin = new TakePointer(p, q, count)
    override val _end = new TakePointer(q, q, count)

    override def take(n: Long) = new TakeRange(base, count + n)
}

class TakePointer[A](_base: Pointer[A], val end: Pointer[A], var count: Long)
        extends PointerRefAdapter[A, A, TakeWhilePointer[A]](_base) {
    countDown
    override def _traversal = base.traversal min ForwardTraversal
    override def _increment { base++/; countDown }
    override def _clone = new TakePointer(base.clone, end, count)

    private def countDown {
        if (base == end)
            return
        if (count == 0)
            baseRef := end
        count = count - 1
    }
}


// TakeWhile

object TakeWhile {
    def apply[A](r: Range[A], f: A => Boolean): Range[A] = new TakeWhileRange(r, f)
}

class TakeWhileRange[A](val base: Range[A], val predicate: A => Boolean) extends Range[A] {
    private val p = base.begin; private val q = base.end
    override val _begin = new TakeWhilePointer(p, q, predicate)
    override val _end = new TakeWhilePointer(q, q, predicate)
}

class TakeWhilePointer[A](_base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerRefAdapter[A, A, TakeWhilePointer[A]](_base) {
    countDown
    override def _traversal = base.traversal min ForwardTraversal
    override def _increment { base++/; countDown }
    override def _clone = new TakeWhilePointer(base.clone, end, predicate)

    private def countDown {
        if (base == end)
            return
        if (!predicate(*(base)))
            baseRef := end
    }
}
