
package mada.rng


object Distance extends Distance

trait Distance extends Predefs {
    class MadaRngDistance[A](_1: Expr[Rng[A]]) {
        def rng_distance = DistanceExpr(_1).expr
    }
    implicit def toMadaRngDistance[A](_1: Expr[Rng[A]]): MadaRngDistance[A] = new MadaRngDistance[A](_1)
}


case class DistanceExpr[A](_1: Expr[Rng[A]]) extends Expr[Long] {
    override def _eval = {
        val z1 = _1.toLazy
        z1.eval.traversal match {
            case RandomAccessTraversal => SizeExpr(z1).eval
            case SinglePassTraversal => FoldLeftExpr(z1, 0L, { (b: Long, a: A) => b + 1 }).eval
        }
    }
}
