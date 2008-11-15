
package mada.rng


object Distance extends Distance

trait Distance extends Predefs {
    class MadaRngDistance[A](_1: Expr[Rng[A]]) {
        def distance = DistanceExpr(_1).expr
    }
    implicit def toMadaRngDistance[A](_1: Expr[Rng[A]]) = new MadaRngDistance(_1)
}


case class DistanceExpr[A](_1: Expr[Rng[A]]) extends Expr[Long] {
    override def _eval = {
        val z1 = _1.toLazy
        z1.eval.traversal match {
            case _: RandomAccessTraversal => SizeExpr(z1).eval
            case _: SinglePassTraversal => FoldLeftExpr(z1, Expr(0: Long), Expr({ (b: Long, a: A) => b + 1 })).eval
        }
    }
}
