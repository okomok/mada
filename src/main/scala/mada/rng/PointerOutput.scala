
package mada.rng


object PointerOutput extends PointerOutput

trait PointerOutput extends Predefs {
    class MadaRngPointerOutput[A](_1: Expr[Pointer[A]]) {
        def output = PointerOutputExpr(_1).expr
    }
    implicit def toMadaRngPointerOutput[A](_1: Expr[Pointer[A]]) = new MadaRngPointerOutput(_1)
}


case class PointerOutputExpr[A](_1: Expr[Pointer[A]]) extends Expr[A => Pointer[A]] {
    override def _eval = PointerOutputImpl(_1.eval, (_: A))
}


object PointerOutputImpl {
    def apply[A](p: Pointer[A], e: A): Pointer[A] = {
        *(p) = e; ++(p)
    }
}
