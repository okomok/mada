
package mada.rng


object Exists extends Exists

trait Exists extends Predefs {
    class MadaRngExists[A](_1: Expr[Rng[A]]) {
        def exists(_2: Expr[A => Boolean]) = ExistsExpr(_1, _2).expr
    }
    implicit def toMadaRngExists[A](_1: Expr[Rng[A]]) = new MadaRngExists(_1)
}


case class ExistsExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Boolean] {
    override def _eval = FindExpr(_1, _2).eval != None
}
