
package mada.rng


object AsRngOf extends AsRngOf; trait AsRngOf extends Predefs {
    class MadaRngAsRngOf[From](_1: Expr.Of[Rng[From]]) {
        def asRngOf[To] = AsRngOfExpr[From, To](_1).expr
    }
    implicit def toMadaRngAsRngOf[From](_1: Expr.Of[Rng[From]]): MadaRngAsRngOf[From] = new MadaRngAsRngOf[From](_1)
}


case class AsRngOfExpr[From, To](_1: Expr.Of[Rng[From]]) extends Expr.Alias[Rng[From], Rng[To]] {
    override protected def _alias = MapExpr(_1, (_: From).asInstanceOf[To])
}
