
package mada.rng


object Outplace extends Outplace; trait Outplace extends Predefs {
    class MadaRngOutplace[A](_1: Expr[Rng[A]]) {
        def outplace = OutplaceExpr(_1).expr
    }
    implicit def toMadaRngOutplace[A](_1: Expr[Rng[A]]): MadaRngOutplace[A] = new MadaRngOutplace[A](_1)
}


case class OutplaceExpr[A](_1: Expr[Rng[A]]) extends ExprAdapter[Rng[Pointer[A]]] {
    override protected def _base = ForceExpr(OutdirectExpr(_1))
}
