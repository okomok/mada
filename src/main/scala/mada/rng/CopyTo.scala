
package mada.rng


object CopyTo extends CopyTo

trait CopyTo extends Predefs {
    class MadaRngCopyTo[From](_1: Expr[Rng[From]]) {
        def copyTo[To >: From](_2: Expr[Pointer[To]]) = CopyToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyTo[From](_1: Expr[Rng[From]]): MadaRngCopyTo[From] = new MadaRngCopyTo[From](_1)
}


case class CopyToExpr[From, To >: From](_1: Expr[Rng[From]], _2: Expr[Pointer[To]]) extends Expr[Pointer[To]] {
    override def _eval = {
        val z2 = _2.toLazy
        ForeachExpr(AsRngOfExpr[From, To](_1), PointerOutputExpr(z2)).eval
        z2.eval
    }
}
