
package mada.rng


object FoldLeft extends FoldLeft

trait FoldLeft extends Predefs {
    class MadaRngFoldLeft[A](_1: Expr[Rng[A]]) {
        def foldLeft[B](_2: Expr[B], _3: Expr[(B, A) => B]) = FoldLeftExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngFoldLeft[A](_1: Expr[Rng[A]]) = new MadaRngFoldLeft(_1)
}


case class FoldLeftExpr[A, B](_1: Expr[Rng[A]], _2: Expr[B], _3: Expr[(B, A) => B]) extends Expr[B] {
    override def _eval = {
        val acc = Ref(_2.eval)
        val a3 = _3.eval
        ForeachExpr(_1, Expr({(e: A) => acc := a3(acc.deref, e)}))
        acc.deref
    }
}
