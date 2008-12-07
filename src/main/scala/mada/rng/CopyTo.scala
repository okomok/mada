
package mada.rng


import Foreach._


object CopyTo extends CopyTo; trait CopyTo extends Predefs {
    class MadaRngCopyTo[From](_1: Expr.Of[Rng[From]]) {
        def copyTo[To >: From](_2: Expr.Of[Pointer[To]]) = CopyToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyTo[From](_1: Expr.Of[Rng[From]]): MadaRngCopyTo[From] = new MadaRngCopyTo[From](_1)
}


case class CopyToExpr[From, To >: From](override val _1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]])
        extends Expr.Method[Rng[From], Pointer[To]] {
    override def _default = {
        val p = _2.eval
        _1.foreach(p.output).eval
        p
    }
}
