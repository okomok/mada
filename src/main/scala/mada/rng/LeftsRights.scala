

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


// lefts

object Lefts extends Lefts; trait Lefts extends Predefs {
    class MadaRngLefts[A, B](_1: Expr.Of[Rng[Either[A, B]]]) {
        def lefts = LeftsExpr(_1).expr
    }
    implicit def toMadaRngLefts[A, B](_1: Expr.Of[Rng[Either[A, B]]]): MadaRngLefts[A, B] = new MadaRngLefts[A, B](_1)
}

case class LeftsExpr[A, B](_1: Expr.Of[Rng[Either[A, B]]]) extends Expr.Alias[Rng[Either[A, B]], Rng[A]] {
    override protected def _alias = MapExpr(FilterExpr(_1, (_: Either[A, B]).isLeft), (_: Either[A, B]).left.get)
}


// rights

object Rights extends Rights; trait Rights extends Predefs {
    class MadaRngRights[A, B](_1: Expr.Of[Rng[Either[A, B]]]) {
        def rights = RightsExpr(_1).expr
    }
    implicit def toMadaRngRights[A, B](_1: Expr.Of[Rng[Either[A, B]]]): MadaRngRights[A, B] = new MadaRngRights[A, B](_1)
}

case class RightsExpr[A, B](_1: Expr.Of[Rng[Either[A, B]]]) extends Expr.Alias[Rng[Either[A, B]], Rng[B]] {
    override protected def _alias = MapExpr(FilterExpr(_1, (_: Either[A, B]).isRight), (_: Either[A, B]).right.get)
}
