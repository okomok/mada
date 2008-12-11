
package mada.rng


// lefts

object Lefts extends Lefts; trait Lefts extends Predefs {
    class MadaRngLefts[A1, A2](_1: Expr.Of[Rng[Either[A1, A2]]]) {
        def lefts = LeftsExpr(_1).expr
    }
    implicit def toMadaRngLefts[A1, A2](_1: Expr.Of[Rng[Either[A1, A2]]]): MadaRngLefts[A1, A2] = new MadaRngLefts[A1, A2](_1)
}

case class LeftsExpr[A1, A2](_1: Expr.Of[Rng[Either[A1, A2]]]) extends Expr.Alias[Rng[Either[A1, A2]], Rng[A1]] {
    override protected def _alias = MapExpr(FilterExpr(_1, (_: Either[A1, A2]).isLeft), (_: Either[A1, A2]).left.get)
}


// rights

object Rights extends Rights; trait Rights extends Predefs {
    class MadaRngRights[A1, A2](_1: Expr.Of[Rng[Either[A1, A2]]]) {
        def rights = RightsExpr(_1).expr
    }
    implicit def toMadaRngRights[A1, A2](_1: Expr.Of[Rng[Either[A1, A2]]]): MadaRngRights[A1, A2] = new MadaRngRights[A1, A2](_1)
}

case class RightsExpr[A1, A2](_1: Expr.Of[Rng[Either[A1, A2]]]) extends Expr.Alias[Rng[Either[A1, A2]], Rng[A2]] {
    override protected def _alias = MapExpr(FilterExpr(_1, (_: Either[A1, A2]).isRight), (_: Either[A1, A2]).right.get)
}
