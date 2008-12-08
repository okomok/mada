
package mada.rng


object Filter extends Filter; trait Filter extends Predefs {
    class MadaRngFilter[A](_1: Expr.Of[Rng[A]]) {
        def filter(_2: A => Boolean) = FilterExpr(_1, _2).expr
    }
    implicit def toMadaRngFilter[A](_1: Expr.Of[Rng[A]]): MadaRngFilter[A] = new MadaRngFilter[A](_1)
}


case class FilterExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Transform[Rng[A]] {
    override protected def _default = _1 match {
        case FilterExpr(x1, x2) => FilterExpr(x1, {(e: A) => x2(e) && _2(e)}).eval // filter-filter fusion
        case _ => FilterImpl(_1.eval, _2)
    }
}


object FilterImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = r.toPair
        new FilterPointer(p, q, f) <=< new FilterPointer(q.copyIn(BidirectionalTraversal), q, f)
    }
}

class FilterPointer[A](override protected val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]] {
    satisfy
    override protected def _traversal = base.traversal upper BidirectionalTraversal
    override protected def _increment = { base.pre_++; satisfy }
    override protected def _copy = new FilterPointer(base.copy, end, predicate)
    override protected def _decrement = { while (!predicate(*(base.pre_--))) { } }
    private def satisfy = { while (base != end && !predicate(*(base))) { base.pre_++ } }

    override def toString = new StringBuilder().append("FilterPointer of ").append(base).toString
}
