
package mada.rng


object Size extends Size

trait Size extends Predefs {
    class MadaRngSize[A](_1: Expr[Rng[A]]) {
        def rng_size = SizeExpr(_1).expr
    }
    implicit def toMadaRngSize[A](_1: Expr[Rng[A]]): MadaRngSize[A] = new MadaRngSize[A](_1)
}


case class SizeExpr[A](_1: Expr[Rng[A]]) extends Expr[Long] {
    override def _eval = SizeImpl(_1.eval)
}


object SizeImpl {
    def apply[A](r: Rng[A]): Long = {
        AssertModels(r, RandomAccessTraversal)
        r.end - r.begin
    }
}
