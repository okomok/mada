
package mada.rng


// Take

object Take extends Take

trait Take extends Predefs {
    class MadaRngTake[A](_1: Expr[Rng[A]]) {
        def take(_2: Expr[Long]) = TakeExpr(_1, _2).expr
    }
    implicit def toMadaRngTake[A](_1: Expr[Rng[A]]): MadaRngTake[A] = new MadaRngTake[A](_1)
}

case class TakeExpr[A](_1: Expr[Rng[A]], _2: Expr[Long]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case TakeExpr(x1, x2) => TakeImpl(x1.eval, x2.eval + _2.eval)
        case _ => TakeImpl(_1.eval, _2.eval)
    }
}

object TakeImpl {
    def apply[A](r: Rng[A], n: Long): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new TakePointer(p, q, n) <=< new TakePointer(q, q, n)
    }
}

class TakePointer[A](override val _base: Pointer[A], val end: Pointer[A], var count: Long)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
    countDown
    override def _traversal = base.traversal upper ForwardTraversal
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

trait TakeWhile extends Predefs {
    class MadaRngTakeWhile[A](_1: Expr[Rng[A]]) {
        def take(_2: Expr[A => Boolean]) = TakeWhileExpr(_1, _2).expr
    }
    implicit def toMadaRngTakeWhile[A](_1: Expr[Rng[A]]): MadaRngTakeWhile[A] = new MadaRngTakeWhile[A](_1)
}

case class TakeWhileExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Rng[A]] {
    override def _eval = TakeWhileImpl(_1.eval, _2.eval)
}

object TakeWhileImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new TakeWhilePointer(p, q, f) <=< new TakeWhilePointer(q, q, f)
    }
}

class TakeWhilePointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, TakeWhilePointer[A]] {
    countDown
    override def _traversal = base.traversal upper ForwardTraversal
    override def _increment { base++/; countDown }
    override def _clone = new TakeWhilePointer(base.clone, end, predicate)

    private def countDown {
        if (base == end)
            return
        if (!predicate(*(base)))
            baseRef := end
    }
}
