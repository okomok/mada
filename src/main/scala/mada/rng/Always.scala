

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Always extends Always; trait Always extends Predefs {
    class MadaRngAlways[A](_1: Expr.Of[Rng[A]]) {
        def always[B](_2: Expr.Of[Rng[B]]) = AlwaysExpr(_1, _2).expr
    }
    implicit def toMadaRngAlways[A](_1: Expr.Of[Rng[A]]): MadaRngAlways[A] = new MadaRngAlways[A](_1)
}


case class AlwaysExpr[A, B](_1: Expr.Of[Rng[A]], _2: Expr.Of[Rng[B]]) extends Expr.Alias[Rng[A], Rng[B]] {
    override protected def _alias = _2
}
