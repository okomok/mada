
package mada.rng


object Force extends Force; trait Force extends Predefs {
    class MadaRngForce[A](_1: ExprV2.Of[Rng[A]]) {
        def force = ForceExpr(_1).expr
    }
    implicit def toMadaRngForce[A](_1: ExprV2.Of[Rng[A]]): MadaRngForce[A] = new MadaRngForce[A](_1)
}


case class ForceExpr[A](_1: ExprV2.Of[Rng[A]]) extends ExprV2.Adapter[Rng[A]] {
    override protected def _base = jcl.FromArrayListExpr(jcl.ToArrayListExpr(_1))
}
