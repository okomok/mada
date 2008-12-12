

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Slice extends Slice; trait Slice extends Predefs {
    class MadaRngSlice[A](_1: Expr.Of[Rng[A]]) {
        def slice(_2: Long, _3: Long) = SliceExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngSlice[A](_1: Expr.Of[Rng[A]]): MadaRngSlice[A] = new MadaRngSlice[A](_1)
}


case class SliceExpr[A](_1: Expr.Of[Rng[A]], _2: Long, _3: Long) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = TakeExpr(DropExpr(_1, _2), _3 - _2)
}
