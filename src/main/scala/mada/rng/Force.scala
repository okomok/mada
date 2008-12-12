
package mada.rng


object Force extends Force; trait Force extends Predefs {
    class MadaRngForce[A](_1: Expr.Of[Rng[A]]) {
        def force = ForceExpr(_1).expr
    }
    implicit def toMadaRngForce[A](_1: Expr.Of[Rng[A]]): MadaRngForce[A] = new MadaRngForce[A](_1)
}


case class ForceExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = jcl.FromArrayListExpr(Expr.Cut(jcl.ToArrayListExpr(_1)))
}
