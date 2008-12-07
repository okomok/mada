
package mada.rng


object AsRngOf extends AsRngOf; trait AsRngOf extends Predefs {
    class MadaRngAsRngOf[From](_1: ExprV2.Of[Rng[From]]) {
        def asRngOf[To] = AsRngOfExpr[From, To](_1).expr
    }
    implicit def toMadaRngAsRngOf[From](_1: ExprV2.Of[Rng[From]]): MadaRngAsRngOf[From] = new MadaRngAsRngOf[From](_1)
}


case class AsRngOfExpr[From, To](_1: ExprV2.Of[Rng[From]]) extends ExprV2.Adapter[Rng[From], Rng[To]] {
    override protected def _base = MapExpr(_1, (_: From).asInstanceOf[To])
}
