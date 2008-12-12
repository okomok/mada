

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Break extends Break; trait Break extends Predefs {
    class MadaRngBreak[A](_1: Expr.Of[Rng[A]]) {
        def break(_2: A => Boolean) = BreakExpr(_1, _2).expr
    }
    implicit def toMadaRngBreak[A](_1: Expr.Of[Rng[A]]): MadaRngBreak[A] = new MadaRngBreak[A](_1)
}


case class BreakExpr[A](_1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Alias[Rng[A], (Rng[A], Rng[A])] {
    override protected def _alias = SpanExpr(_1, !_2(_))
}
