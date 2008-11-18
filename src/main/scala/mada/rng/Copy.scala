
package mada.rng


object Copy extends Copy

trait Copy extends Predefs {
    class MadaRngCopy[A](_1: Expr[Rng[A]]) {
        def copy = CopyExpr(_1).expr
    }
    implicit def toMadaRngCopy[A](_1: Expr[Rng[A]]): MadaRngCopy[A] = new MadaRngCopy[A](_1)
}


case class CopyExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = FromArrayListExpr(ToArrayListExpr(_1)).eval
}
