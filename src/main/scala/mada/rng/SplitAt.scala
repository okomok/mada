
package mada.rng


import Drop._


object SplitAt extends SplitAt; trait SplitAt extends Predefs {
    class MadaRngSplitAt[A](_1: Expr.Of[Rng[A]]) {
        def splitAt(_2: Long) = SplitAtExpr(_1, _2).expr
    }
    implicit def toMadaRngSplitAt[A](_1: Expr.Of[Rng[A]]): MadaRngSplitAt[A] = new MadaRngSplitAt[A](_1)
}


case class SplitAtExpr[A](override val _1: Expr.Of[Rng[A]], _2: Long) extends Expr.Method[Rng[A], (Rng[A], Rng[A])] {
    override protected def _default = SplitAtImpl(_1.eval, _2)
}


object SplitAtImpl {
    def apply[A](r: Rng[A], n: Long): (Rng[A], Rng[A]) = {
        AssertModels(r, Traversal.Forward)
        val r2 = r./.drop(n)./
        (r.begin <=< r2.begin, r2)
    }
}
