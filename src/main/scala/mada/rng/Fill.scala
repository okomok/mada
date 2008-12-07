
package mada.rng


import Pointer._


object Fill extends Fill; trait Fill extends Predefs {
    class MadaRngFill[A](_1: Expr.Of[Rng[A]]) {
        def fill[B <: A](_2: B) = FillExpr(_1, _2).expr
    }
    implicit def toMadaRngFill[A](_1: Expr.Of[Rng[A]]): MadaRngFill[A] = new MadaRngFill[A](_1)
}


case class FillExpr[A, B <: A](_1: Expr.Of[Rng[A]], _2: B) extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = ForeachExpr(OutdirectExpr(_1), *(_: Pointer[A]) = _2)
}
