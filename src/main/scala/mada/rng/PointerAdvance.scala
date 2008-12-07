
package mada.rng


import Pointer._


object PointerAdvance extends PointerAdvance; trait PointerAdvance extends Predefs {
    class MadaRngPointerAdvance[A](_1: Expr.Of[Pointer[A]]) {
        def ptr_advance(_2: Long) = PointerAdvanceExpr(_1, _2).expr
    }
    implicit def toMadaRngPointerAdvance[A](_1: Expr.Of[Pointer[A]]): MadaRngPointerAdvance[A] = new MadaRngPointerAdvance[A](_1)
}


case class PointerAdvanceExpr[A](override val _1: Expr.Of[Pointer[A]], _2: Long)
        extends Expr.Method[Pointer[A], Pointer[A]] {
    override def _default = PointerAdvanceImpl(_1.eval, _2)
}


object PointerAdvanceImpl {
    def apply[A](p: Pointer[A], dd : Long): Pointer[A] = {
        var d = dd
        p.traversal match {
            case _: RandomAccessTraversal => p += d
            case _: BidirectionalTraversal => {
                if (d >= 0)
                    while (d != 0) { ++(p); d -= 1 }
                else
                    while (d != 0) { --(p); d += 1 }
            }
            case _: SinglePassTraversal => while (d != 0) { ++(p); d -= 1 }
        }
        p
    }
}
