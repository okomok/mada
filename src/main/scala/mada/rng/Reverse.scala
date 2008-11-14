
package mada.rng


object Reverse extends Reverse

trait Reverse extends Predefs {
    class MadaRngReverse[A](_1: Expr[Rng[A]]) {
        def reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaRngReverse[A](_1: Expr[Rng[A]]) = new MadaRngReverse(_1)
}


case class ReverseExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def eval = _1 match {
        case ReverseExpr(a1) => a1.eval
        case _ => ReverseImpl(_1.eval)
    }
}


object ReverseImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        Assert("requires BidirectionalRng", r models BidirectionalTraversal)
        val (p, q) = (r.end, r.begin) // order matters!
        new ReversePointer(p) <=< new ReversePointer(q)
    }
}

class ReversePointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReversePointer[A]] {
    override def _increment { base--/ }
    override def _clone = new ReversePointer(base.clone)
    override def _decrement { base++/ }
    override def _offset(d: Long) { base -= d }
    override def _difference(that: ReversePointer[A]) = that.base - base
}
