
package mada.rng


object CopyTo extends CopyTo

trait CopyTo extends Predefs {
    class MadaRngCopyTo[From](_1: Expr[Rng[From]]) {
        def copyTo[To >: From](_2: Expr[Pointer[To]]) = CopyToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyTo[From](_1: Expr[Rng[From]]) = new MadaRngCopyTo(_1)
}


case class CopyToExpr[From, To >: From](_1: Expr[Rng[From]], _2: Expr[Pointer[To]]) extends Expr[Pointer[To]] {
    def eval = {
        val x2 = _2.toLazy
        ForeachExpr(AsRngOfExpr[From, To](_1), PointerOutputExpr(x2)).eval
        x2.eval
    }
}
