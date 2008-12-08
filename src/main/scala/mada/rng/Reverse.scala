
package mada.rng


object Reverse extends Reverse; trait Reverse extends Predefs {
    class MadaRngReverse[A](_1: Expr.Of[Rng[A]]) {
        def reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaRngReverse[A](_1: Expr.Of[Rng[A]]): MadaRngReverse[A] = new MadaRngReverse[A](_1)
}


case class ReverseExpr[A](_1: Expr.Of[Rng[A]]) extends Expr[Rng[A], Rng[A]] {
    override protected def _eval[U](x: Expr[Rng[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ReverseExpr(x1) => x1.eval // reverse-reverse fusion
            case _ => ReverseImpl(_1.eval)
        }
        case _ => dontKnow(x)
    }
}


object ReverseImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertModels(r, BidirectionalTraversal)
        new ReversePointer(r.end) <=< new ReversePointer(r.begin)
    }
}

class ReversePointer[A](override protected val _base: Pointer[A])
        extends PointerAdapter[A, A, ReversePointer[A]] {
    override protected def _read = base.copy.pre_--.read
    override protected def _write(e: A) = { base.copy.pre_--.write(e) }
    override protected def _increment = { base.pre_-- }
    override protected def _copy = new ReversePointer(base.copy)
    override protected def _decrement = { base.pre_++ }
    override protected def _offset(d: Long) = { base -= d }
    override protected def _difference(that: ReversePointer[A]) = that.base - base
}
