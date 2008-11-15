
package mada.rng


object Foreach extends Foreach

trait Foreach extends Predefs {
    class MadaRngForeach[A](_1: Expr[Rng[A]]) {
        def foreach[X](_2: Expr[A => X]) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaRngForeach[A](_1: Expr[Rng[A]]) = new MadaRngForeach(_1)
}


case class ForeachExpr[A, X](_1: Expr[Rng[A]], _2: Expr[A => X]) extends Expr[Unit] {
    def eval = {
        val a2 = _2.eval
        LoopExpr(_1, Expr({ (e: A) => a2(e); true })).eval
    }
}
