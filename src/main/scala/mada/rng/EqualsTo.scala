
package mada.rng


import End._
import Mismatch._


object EqualsTo extends EqualsTo; trait EqualsTo extends Predefs {
    class MadaRngEqualsTo[A1](_1: Expr[Rng[A1]]) {
        def equalsTo[A2](_2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) = EqualsToExpr(_1, _2, _3).expr
        def equalsTo(_2: Expr[Pointer[A1]]) = EqualsToExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngEqualsTo[A1](_1: Expr[Rng[A1]]): MadaRngEqualsTo[A1] = new MadaRngEqualsTo[A1](_1)
}


case class EqualsToExpr[A1, A2](_1: Expr[Rng[A1]], _2: Expr[Pointer[A2]], _3: (A1, A2) => Boolean) extends Expr[Boolean] {
    override def _eval = {
        val z1 = _1.xlazy
        z1.mismatch(_2, _3).eval._1 == z1.end.eval
    }
}
