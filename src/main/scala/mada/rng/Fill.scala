
package mada.rng


import Pointer._


object Fill extends Fill; trait Fill extends Predefs {
    class MadaRngFill[A](_1: Expr[Rng[A]]) {
        def fill[B <: A](_2: B) = FillExpr(_1, _2).expr
    }
    implicit def toMadaRngFill[A](_1: Expr[Rng[A]]): MadaRngFill[A] = new MadaRngFill[A](_1)
}


case class FillExpr[A, B <: A](_1: Expr[Rng[A]], _2: B) extends ExprAdapter[Unit] {
    override protected def _base = ForeachExpr(OutdirectExpr(_1), *(_: Pointer[A]) = _2)
}
