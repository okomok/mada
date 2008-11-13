
package mada.rng


object AsRngOf extends AsRngOf

trait AsRngOf {
    class MadaRngAsRngOf[From](_1: Expr[Rng[From]]) {
        def asRngOf[To] = AsRngOfExpr[From, To](_1).expr
    }
    implicit def toMadaRngAsRngOf[From](_1: Expr[Rng[From]]) = new MadaRngAsRngOf(_1)
}


case class AsRngOfExpr[From, To](_1: Expr[Rng[From]]) extends Expr[Rng[To]] {
    def eval = MapExpr(_1, (_: From).asInstanceOf[To]).eval
}
