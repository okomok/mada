
package mada.rng


// Take

object Take {
    def apply[A](base: Rng[A], count: Long): Rng[A] = new TakeRng(base, count)
}

class TakeRng[A](val base: Rng[A], val count: Long) extends Rng[A] {
    private val p = base.begin
    private val q = base.end
    override val _begin = new TakePointer(p, q, count)
    override val _end = new TakePointer(q, q, count)

    override def take(n: Long) = new TakeRng(base, count + n)
}

class TakePointer[A](override val _base: Pointer[A], val end: Pointer[A], var count: Long)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
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
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = new TakeWhileRng(r, f)
}

class TakeWhileRng[A](val base: Rng[A], val predicate: A => Boolean) extends Rng[A] {
    private val p = base.begin; private val q = base.end
    override val _begin = new TakeWhilePointer(p, q, predicate)
    override val _end = new TakeWhilePointer(q, q, predicate)
}

class TakeWhilePointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
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
