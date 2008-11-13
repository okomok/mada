
package mada.rng


object Distance extends Distance

trait Distance {
    class MadaRngDistance[A](_1: Expr[Rng[A]]) {
        def distance = DistanceExpr(_1).expr
    }
    implicit def toMadaRngDistance[A](_1: Expr[Rng[A]]) = new MadaRngDistance(_1)
}


case class DistanceExpr[A](_1: Expr[Rng[A]]) extends Expr[Long] {
    def eval = {
        val x1 = _1.toLazy
        x1.eval.traversal match {
            case _: RandomAccessTraversal => SizeExpr(x1).eval
            case _: SinglePassTraversal => FoldLeftExpr(x1, Expr(0: Long), Expr({(b: Long, a: A) => b + 1})).eval
        }
    }
}
