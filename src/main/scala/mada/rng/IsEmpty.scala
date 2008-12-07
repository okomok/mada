
package mada.rng


object IsEmpty extends IsEmpty; trait IsEmpty extends Predefs {
    class MadaRngIsEmpty[A](_1: ExprV2.Of[Rng[A]]) {
        def isEmpty = IsEmptyExpr(_1).expr
    }
    implicit def toMadaRngIsEmpty[A](_1: ExprV2.Of[Rng[A]]): MadaRngIsEmpty[A] = new MadaRngIsEmpty[A](_1)
}


case class IsEmptyExpr[A](override val _1: ExprV2.Of[Rng[A]]) extends ExprV2.Method[Rng[A], Boolean] {
    override def _default = IsEmptyImpl(_1.eval)
}


object IsEmptyImpl {
    def apply[A](r: Rng[A]): Boolean = r.begin == r.end
}
