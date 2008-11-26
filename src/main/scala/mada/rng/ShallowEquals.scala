
package mada.rng


object ShallowEquals extends ShallowEquals

trait ShallowEquals extends Predefs {
    class MadaRngShallowEquals[A](_1: Expr[Rng[A]]) {
        def rng_shallowEquals(_2: Expr[Rng[A]]) = ShallowEqualsExpr(_1, _2).expr
    }
    implicit def toMadaRngShallowEquals[A](_1: Expr[Rng[A]]): MadaRngShallowEquals[A] = new MadaRngShallowEquals[A](_1)
}


case class ShallowEqualsExpr[A](_1: Expr[Rng[A]], _2: Expr[Rng[A]]) extends Expr[Boolean] {
    override def _eval = {
        val (r1, r2) = (_1.eval, _2.eval)
        (r1.begin == r2.begin) && (r1.end == r2.end)
    }
}
