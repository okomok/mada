
package mada.rng


object Filter extends Filter

trait Filter extends Predefs {
    class MadaRngFilter[A](_1: Expr[Rng[A]]) {
        def filter(_2: Expr[A => Boolean]) = FilterExpr(_1, _2).expr
    }
    implicit def toMadaRngFilter[A](_1: Expr[Rng[A]]): MadaRngFilter[A] = new MadaRngFilter[A](_1)
}


case class FilterExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case FilterExpr(x1, x2) => { // filter-filter fusion
            val a2 = _2.eval
            val b2 = x2.eval
            FilterImpl(x1.eval, {(e: A) => a2(e) && b2(e)})
        }
        case _ => FilterImpl(_1.eval, _2.eval)
    }
}


object FilterImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Rng[A] = {
        val (p, q) = (r.begin, r.end)
        new FilterPointer(p, q, f) <=< new FilterPointer(q.cloneIn(BidirectionalTraversal), q, f)
    }
}

class FilterPointer[A](override val _base: Pointer[A], val end: Pointer[A], val predicate: A => Boolean)
        extends PointerAdapter[A, A, FilterPointer[A]] {
    satisfy
    override def _traversal = base.traversal upper BidirectionalTraversal
    override def _increment { base++/; satisfy }
    override def _clone = new FilterPointer(base.clone, end, predicate)
    override def _decrement { while (!predicate(*(base--/))) { } }
    private def satisfy { while (base != end && !predicate(*(base))) { base++/ } }
}
