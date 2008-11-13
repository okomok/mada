
package mada.rng


object Foreach extends Foreach

trait Foreach {
    class MadaRngForeach[A](_1: Expr[Rng[A]]) {
        def foreach[X](_2: A => X) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaRngForeach[A](_1: Expr[Rng[A]]) = new MadaRngForeach(_1)
}


case class ForeachExpr[A, X](_1: Expr[Rng[A]], _2: A => X) extends Expr[Rng[A]] {
    def eval = ForeachImpl(_1.eval, _2)
}


object ForeachImpl {
    def apply[A, X](r: Rng[A], f: A => X): Rng[A] = {
        val p = r.begin; val q = r.end
        while (p != q) {
            f(*(p))
            ++(p)
        }
        r
    }
}
