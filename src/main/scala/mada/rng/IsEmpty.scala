
package mada.rng


object IsEmpty extends IsEmpty

trait IsEmpty extends Predefs {
    class MadaRngIsEmpty[A](_1: Expr[Rng[A]]) {
        def isEmpty = IsEmptyExpr(_1).expr
    }
    implicit def toMadaRngIsEmpty[A](_1: Expr[Rng[A]]): MadaRngIsEmpty[A] = new MadaRngIsEmpty[A](_1)
}


case class IsEmptyExpr[A](_1: Expr[Rng[A]]) extends Expr[Boolean] {
    override def _eval = IsEmptyImpl(_1.eval)
}


object IsEmptyImpl {
    def apply[A](r: Rng[A]): Boolean = r.begin == r.end
}
