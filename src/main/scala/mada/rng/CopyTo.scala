
package mada.rng


import Foreach._


object CopyTo extends CopyTo

trait CopyTo extends Predefs {
    class MadaRngCopyTo[From](_1: Expr[Rng[From]]) {
        def rng_copyTo[To >: From](_2: Expr[Pointer[To]]) = CopyToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyTo[From](_1: Expr[Rng[From]]): MadaRngCopyTo[From] = new MadaRngCopyTo[From](_1)
}


case class CopyToExpr[From, To >: From](_1: Expr[Rng[From]], _2: Expr[Pointer[To]]) extends Expr[Pointer[To]] {
    override def _eval = {
        val p = _2.eval
        _1.rng_foreach(p.output).eval
        p
    }
}
