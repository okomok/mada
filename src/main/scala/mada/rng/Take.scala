
package mada.rng


object Take extends Take

trait Take extends Predefs {
    class MadaRngTake[A](_1: Expr[Rng[A]]) {
        def rng_take(_2: Long) = TakeExpr(_1, _2).expr
    }
    implicit def toMadaRngTake[A](_1: Expr[Rng[A]]): MadaRngTake[A] = new MadaRngTake[A](_1)
}


case class TakeExpr[A](_1: Expr[Rng[A]], _2: Long) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case TakeExpr(x1, x2) => TakeImpl(x1.eval, x2 + _2)
        case _ => TakeImpl(_1.eval, _2)
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
    override def _increment { base.pre_++; countDown }
    override def _copy = new TakePointer(base.copy, end, count)

    private def countDown {
        if (base == end)
            return
        if (count == 0)
            baseRef := end
        count = count - 1
    }
}
