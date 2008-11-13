
package mada.rng


object Forall extends Forall

trait Forall {
    class MadaRngForall[A](_1: Expr[Rng[A]]) {
        def find(_2: Expr[A => Boolean]) = ForallExpr(_1, _2).expr
    }
    implicit def toMadaRngForall[A](_1: Expr[Rng[A]]) = new MadaRngForall(_1)
}

case class ForallExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Boolean] {
    def eval = {
        val not_2: A => Boolean = !_2.eval.apply(_)
        FindExpr(_1, Expr(not_2)).eval == None
    }
}
