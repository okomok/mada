
package mada.rng


// Take

object Take extends Take

trait Take {
    class MadaRngTake[A](_1: Expr[Rng[A]]) {
        def take(_2: Long) = TakeExpr(_1, _2).expr
    }
    implicit def toMadaRngTake[A](_1: Expr[Rng[A]]) = new MadaRngTake(_1)
}

case class TakeExpr[A](_1: Expr[Rng[A]], _2: Long) extends Expr[Rng[A]] {
    def eval = _1 match {
        case TakeExpr(a1, a2) => new TakeRng(a1.eval, a2 + _2)
        case _ => new TakeRng(_1.eval, _2)
    }
}

class TakeRng[A](val base: Rng[A], val count: Long) extends Rng[A] {
    private val p = base.begin
    private val q = base.end
    override val _begin = new TakePointer(p, q, count)
    override val _end = new TakePointer(q, q, count)

    def toExpr = TakeExpr(Expr(base), count)
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

object TakeWhile extends TakeWhile

trait TakeWhile {
    class MadaRngTakeWhile[A](_1: Expr[Rng[A]]) {
        def take(_2: A => Boolean) = TakeWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngTakeWhile[A](_1: Expr[Rng[A]]) = new MadaRngTakeWhile(_1)
}

case class TakeWhileExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Rng[A]] {
    def eval = new TakeWhileRng(_1.eval, _2)
}

class TakeWhileRng[A](val base: Rng[A], val predicate: A => Boolean) extends Rng[A] {
    private val p = base.begin; private val q = base.end
    override val _begin = new TakeWhilePointer(p, q, predicate)
    override val _end = new TakeWhilePointer(q, q, predicate)

    def toExpr = TakeWhileExpr(Expr(base), predicate)
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
