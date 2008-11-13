
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
        val x = _1.toLazy
        x.eval.traversal match {
            case _: RandomAccessTraversal => SizeExpr(x).eval
            case _: SinglePassTraversal => FoldLeftExpr(x, Expr(0: Long), Expr({(b: Long, a: A) => b + 1})).eval
        }
    }
}
/*
object DistanceImpl {
    def apply[A](r: Rng[A]): Long = r.traversal match {
        case _: RandomAccessTraversal => r.size
        case _: SinglePassTraversal => r.foldLeft(0: Long, {(b: Long, a: A) => b + 1})
    }
}
*/
