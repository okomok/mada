
package mada.rng


object Empty extends Empty; trait Empty extends Predefs {
    class MadaRngEmpty[A](_1: Expr.Of[Rng[A]]) {
        def empty = EmptyExpr(_1).expr
    }
    implicit def toMadaRngEmpty[A](_1: Expr.Of[Rng[A]]): MadaRngEmpty[A] = new MadaRngEmpty[A](_1)
}


case class EmptyExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Transform[Rng[A]] {
    override def _default = _1 match {
        case EmptyExpr(x1) => EmptyExpr(x1).eval // empty-empty fusion
        case _ => EmptyImpl(_1.eval)
    }
}


object EmptyImpl {
    def apply[A](r: Rng[A]): Rng[A] = { val q = r.end; q <=< q }
}
