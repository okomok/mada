
package mada.rng


object Size extends Size; trait Size extends Predefs {
    class MadaRngSize[A](_1: ExprV2.Of[Rng[A]]) {
        def size = SizeExpr(_1).expr
    }
    implicit def toMadaRngSize[A](_1: ExprV2.Of[Rng[A]]): MadaRngSize[A] = new MadaRngSize[A](_1)
}


case class SizeExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], Long] {
    override def _default = SizeImpl(_1.eval)
}


object SizeImpl {
    def apply[A](r: Rng[A]): Long = {
        AssertModels(r, RandomAccessTraversal)
        r.end - r.begin
    }
}
