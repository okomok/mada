
package mada.rng


object CopyTo extends CopyTo

trait CopyTo {
    class MadaRngCopyTo[From](_1: Expr[Rng[From]]) {
        def find[To >: From](_2: Expr[Pointer[To]]) = CopyToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyTo[From](_1: Expr[Rng[From]]) = new MadaRngCopyTo(_1)
}


case class CopyToExpr[From, To >: From](_1: Expr[Rng[From]], _2: Expr[Pointer[To]])
        extends Expression[Pointer[To]]({
            val p2 = _2.eval
            ForeachExpr(_1, p2.output).eval
            p2
        })

