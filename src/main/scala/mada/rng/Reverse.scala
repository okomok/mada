
package mada.rng


object Reverse extends Reverse; trait Reverse extends Predefs {
    class MadaRngReverse[A](_1: Expr[Rng[A]]) {
        def rng_reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaRngReverse[A](_1: Expr[Rng[A]]): MadaRngReverse[A] = new MadaRngReverse[A](_1)
}


case class ReverseExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = ReverseImpl(_1.eval)
    override def _eval[U](c: Context[Rng[A], U]) = _1 match {
        case ReverseExpr(x1) => x1.eval(c) // reverse-reverse fusion
        case _ => super._eval(c)
    }
}


object ReverseImpl {
    def apply[A](r: Rng[A]): Rng[A] = {
        AssertModels(r, BidirectionalTraversal)
        val (p, q) = (r.end, r.begin) // order matters!
        new ReversePointer(p) <=< new ReversePointer(q)
    }
}

class ReversePointer[A](override val _base: Pointer[A])
        extends PointerAdapter[A, A, ReversePointer[A]] {
    override def _read = base.copy.pre_--.read
    override def _write(e: A) { base.copy.pre_--.write(e) }
    override def _increment { base.pre_-- }
    override def _copy = new ReversePointer(base.copy)
    override def _decrement { base.pre_++ }
    override def _offset(d: Long) { base -= d }
    override def _difference(that: ReversePointer[A]) = that.base - base
}
