
package mada.rng


object Take extends Take; trait Take extends Predefs {
    class MadaRngTake[A](_1: Expr.Of[Rng[A]]) {
        def take(_2: Long) = TakeExpr(_1, _2).expr
    }
    implicit def toMadaRngTake[A](_1: Expr.Of[Rng[A]]): MadaRngTake[A] = new MadaRngTake[A](_1)
}


case class TakeExpr[A](override val _1: Expr.Of[Rng[A]], _2: Long) extends Expr.Transform[Rng[A]] {
    override protected def _default = _1 match {
        case TakeExpr(x1, x2) => TakeExpr(x1, Math.min(x2, _2)).eval // take-take fusion
        case _ => TakeImpl(_1.eval, _2)
    }
}


object TakeImpl {
    def apply[A](r: Rng[A], n: Long): Rng[A] = {
        val (p, q) = r.toPair
        new TakePointer(p, q, n) <=< new TakePointer(q, q, n)
    }
}

class TakePointer[A](override val _base: Pointer[A], val end: Pointer[A], var count: Long)
        extends PointerAdapter[A, A, TakePointer[A]] {
    taken
    override protected def _traversal = base.traversal upper ForwardTraversal
    override protected def _increment = { base.pre_++; count -= 1; taken }
    override protected def _copy = new TakePointer(base.copy, end, count)
    override def toString = new StringBuilder().append("TakePointer of ").append(base).toString

    private def taken = {
        if (count == 0) {
            baseRef := end
        }
    }
}
