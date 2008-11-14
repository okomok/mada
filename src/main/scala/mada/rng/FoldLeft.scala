
package mada.rng


object FoldLeft extends FoldLeft

trait FoldLeft extends Predefs {
    class MadaRngFoldLeft[A](_1: Expr[Rng[A]]) {
        def foldLeft[B](_2: Expr[B], _3: Expr[(B, A) => B]) = FoldLeftExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngFoldLeft[A](_1: Expr[Rng[A]]) = new MadaRngFoldLeft(_1)
}


case class FoldLeftExpr[A, B](_1: Expr[Rng[A]], _2: Expr[B], _3: Expr[(B, A) => B]) extends Expr[B] {
    def eval = FoldLeftImpl(_1.eval, _2.eval, _3.eval)
}


object FoldLeftImpl {
    def apply[A, B](r: Rng[A], z: B, op: (B, A) => B): B = {
        val (p, q) = (r.begin, r.end)
        var acc = z
        while (p != q) {
            acc = op(acc, *(p))
            ++(p)
        }
        acc
    }
}
