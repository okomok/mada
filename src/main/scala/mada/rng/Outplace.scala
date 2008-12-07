
package mada.rng


object Outplace extends Outplace; trait Outplace extends Predefs {
    class MadaRngOutplace[A](_1: Expr.Of[Rng[A]]) {
        def outplace = OutplaceExpr(_1).expr
    }
    implicit def toMadaRngOutplace[A](_1: Expr.Of[Rng[A]]): MadaRngOutplace[A] = new MadaRngOutplace[A](_1)
}


case class OutplaceExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Alias[Rng[A], Rng[Pointer[A]]] {
    override protected def _alias = ForceExpr(OutdirectExpr(_1))
}
