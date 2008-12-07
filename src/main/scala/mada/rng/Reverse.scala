
package mada.rng


object Reverse extends Reverse; trait Reverse extends Predefs {
    class MadaRngReverse[A](_1: ExprV2.Of[Rng[A]]) {
        def reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaRngReverse[A](_1: ExprV2.Of[Rng[A]]): MadaRngReverse[A] = new MadaRngReverse[A](_1)
}


case class ReverseExpr[A](_1: ExprV2.Of[Rng[A]]) extends ExprV2[Rng[A], Rng[A]] {
    override def _eval[U](x: ExprV2[Rng[A], U]): U = x match {
        case Self => _1.eval(this)
        case Default => _1 match {
            case ReverseExpr(x1) => x1.eval(x) // reverse-reverse fusion
            case _ => ReverseImpl(_1.eval)
        }
        case _ => unknown(x)
    }
}


object ReverseImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertModels(r, BidirectionalTraversal)
        new ReversePointer(r.end) <=< new ReversePointer(r.begin)
    }
}

class ReversePointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReversePointer[A]] {
    override def _read = base.copy.pre_--.read
    override def _write(e: A) = { base.copy.pre_--.write(e) }
    override def _increment = { base.pre_-- }
    override def _copy = new ReversePointer(base.copy)
    override def _decrement = { base.pre_++ }
    override def _offset(d: Long) = { base -= d }
    override def _difference(that: ReversePointer[A]) = that.base - base
}
