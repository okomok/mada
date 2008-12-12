

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Identity extends Identity; trait Identity extends Predefs {
    class MadaRngIdentity[A](_1: Expr.Of[Rng[A]]) {
        def identity = IdentityExpr(_1).expr
    }
    implicit def toMadaRngIdentity[A](_1: Expr.Of[Rng[A]]): MadaRngIdentity[A] = new MadaRngIdentity[A](_1)
}


case class IdentityExpr[A](_1: Expr.Of[Rng[A]]) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = _1
}
