
package mada.rng


object PointerAdvance extends PointerAdvance

trait PointerAdvance {
    class MadaRngPointerAdvance[A](_1: Expr[Pointer[A]]) {
        def advance(_2: Expr[Long]) = PointerAdvanceExpr(_1, _2).expr
    }
    implicit def toMadaRngPointerAdvance[A](_1: Expr[Pointer[A]]) = new MadaRngPointerAdvance(_1)
}


case class PointerAdvanceExpr[A](_1: Expr[Pointer[A]], _2: Expr[Long]) extends Expr[Pointer[A]] {
    def eval = PointerAdvanceImpl(_1.eval, _2.eval)
}


object PointerAdvanceImpl {
    def apply[A](p: Pointer[A], dd : Long): Pointer[A] = {
        var d = dd
        p.traversal match {
            case _: RandomAccessTraversal => p += d
            case _: BidirectionalTraversal => {
                if (d >= 0)
                    while (d != 0) { ++(p); d = d - 1 }
                else
                    while (d != 0) { --(p); d = d + 1 }
            }
            case _ => while (d != 0) { ++(p); d = d - 1 }
        }
        p
    }
}
