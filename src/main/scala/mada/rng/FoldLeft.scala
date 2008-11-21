
package mada.rng


object FoldLeft extends FoldLeft

trait FoldLeft extends Predefs {
    class MadaRngFoldLeft[A](_1: Expr[Rng[A]]) {
        def rng_foldLeft[B](_2: B, _3: (B, A) => B) = FoldLeftExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngFoldLeft[A](_1: Expr[Rng[A]]): MadaRngFoldLeft[A] = new MadaRngFoldLeft[A](_1)
}


case class FoldLeftExpr[A, B](_1: Expr[Rng[A]], _2: B, _3: (B, A) => B) extends Expr[B] {
    override def _eval = {
        val acc = new Ref(_2)
        ForeachExpr(_1, acc := _3(acc.deref, (_: A))).eval
        acc.deref
    }
}
