
package mada.rng


object PointerSwap extends PointerSwap

trait PointerSwap extends Predefs {
    class MadaRngPointerSwap[A](_1: Expr[Pointer[A]]) {
        def swap(_2: Expr[Pointer[A]]) = PointerSwapExpr(_1, _2).expr
    }
    implicit def toMadaRngPointerSwap[A](_1: Expr[Pointer[A]]) = new MadaRngPointerSwap(_1)
}


case class PointerSwapExpr[A](_1: Expr[Pointer[A]], _2: Expr[Pointer[A]]) extends Expr[Unit] {
    override def _eval = PointerSwapImpl(_1.eval, _2.eval)
}


object PointerSwapImpl {
    def apply[A](p: Pointer[A], q: Pointer[A]) {
        val tmp = *(p)
        *(p) = *(q)
        *(q) = tmp
    }
}
