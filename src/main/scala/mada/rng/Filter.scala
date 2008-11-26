
package mada.rng


object Filter extends Filter

trait Filter extends Predefs {
    class MadaRngFilter[A](_1: Expr[Rng[A]]) {
        def rng_filter(_2: A => Boolean) = FilterExpr(_1, _2).expr
    }
    implicit def toMadaRngFilter[A](_1: Expr[Rng[A]]): MadaRngFilter[A] = new MadaRngFilter[A](_1)
}


case class FilterExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case FilterExpr(x1, x2) => FilterImpl(x1.eval, {(e: A) => x2(e) && _2(e)}) // filter-filter fusion
        case _ => FilterImpl(_1.eval, _2)
    }
}


object FilterImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new FilterPointer(p, q, f) <=< new FilterPointer(q.copyIn(BidirectionalTraversal), q, f)
    }
}

class FilterPointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]] {
    satisfy
    override def _traversal = base.traversal upper BidirectionalTraversal
    override def _increment { base.pre_++; satisfy }
    override def _copy = new FilterPointer(base.copy, end, predicate)
    override def _decrement { while (!predicate(*(base.pre_--))) { } }
    private def satisfy { while (base != end && !predicate(*(base))) { base.pre_++ } }
}
