
package mada.rng


object Size extends Size

trait Size {
    class MadaRngSize[A](_1: Expr[Rng[A]]) {
        def size = SizeExpr(_1).expr
    }
    implicit def toMadaRngSize[A](_1: Expr[Rng[A]]) = new MadaRngSize(_1)
}


case class SizeExpr[A](_1: Expr[Rng[A]]) extends Expr[Long] {
    def eval = SizeImpl(_1.eval)
}


object SizeImpl {
    def apply[A](r: Rng[A]): Long = {
        Assert("requires RandomAccessRng", r models RandomAccessTraversal)
        r.end - r.begin
    }
}
