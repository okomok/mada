
package mada.rng


object ShallowEquals extends ShallowEquals; trait ShallowEquals extends Predefs {
    class MadaRngShallowEquals[A](_1: Expr.Of[Rng[A]]) {
        def shallowEquals(_2: Expr.Of[Rng[A]]) = ShallowEqualsExpr(_1, _2).expr
    }
    implicit def toMadaRngShallowEquals[A](_1: Expr.Of[Rng[A]]): MadaRngShallowEquals[A] = new MadaRngShallowEquals[A](_1)
}


case class ShallowEqualsExpr[A](override val _1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Boolean] {
    override protected def _default = {
        val (r1, r2) = (_1.eval, _2.eval)
        (r1.begin == r2.begin) && (r1.end == r2.end)
    }
}
