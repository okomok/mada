
package mada.rng


object EqualsTo extends EqualsTo

trait EqualsTo extends Predefs {
    class MadaRngEqualsTo[A1](_1: Expr[Rng[A1]]) {
        def rng_equalsTo[A2](_2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) = EqualsToExpr(_1, _2, _3).expr
        def rng_equalsTo(_2: Expr[Pointer[A1]]) = EqualsToExpr(_1, _2, (_: A1) == (_: A1)).expr
    }
    implicit def toMadaRngEqualsTo[A1](_1: Expr[Rng[A1]]): MadaRngEqualsTo[A1] = new MadaRngEqualsTo[A1](_1)
}


case class EqualsToExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) extends Expr[Boolean] {
    override def _eval = {
        val z1 = _1.toLazy
        MismatchExpr(z1, _2, _3).eval._1 == EndExpr(z1).eval
    }
}
