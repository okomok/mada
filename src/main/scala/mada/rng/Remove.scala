

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Remove extends Remove; trait Remove extends Predefs {
    class MadaRngRemove[A](_1: Expr.Of[Rng[A]]) {
        def remove(_2: A => Boolean) = RemoveExpr(_1, _2).expr
    }
    implicit def toMadaRngRemove[A](_1: Expr.Of[Rng[A]]): MadaRngRemove[A] = new MadaRngRemove[A](_1)
}


case class RemoveExpr[A](_1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = FilterExpr(_1, !_2(_: A))
}
