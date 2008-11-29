
package mada.rng


import Map._


object AsRngOf extends AsRngOf; trait AsRngOf extends Predefs {
    class MadaRngAsRngOf[From](_1: Expr[Rng[From]]) {
        def rng_asRngOf[To] = AsRngOfExpr[From, To](_1).expr
    }
    implicit def toMadaRngAsRngOf[From](_1: Expr[Rng[From]]): MadaRngAsRngOf[From] = new MadaRngAsRngOf[From](_1)
}


case class AsRngOfExpr[From, To](_1: Expr[Rng[From]]) extends Expr[Rng[To]] {
    override def _eval[U](c: Context[Rng[To], U]) = _1.rng_map((_: From).asInstanceOf[To]).eval(c)
}
