

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Fill extends Fill; trait Fill extends Predefs {
    class MadaRngStlFill[A](_1: Expr.Of[Rng[A]]) {
        def stl_fill[B <: A](_2: B) = FillExpr(_1, _2).expr
    }
    implicit def toMadaRngStlFill[A](_1: Expr.Of[Rng[A]]): MadaRngStlFill[A] = new MadaRngStlFill[A](_1)
}


case class FillExpr[A, B <: A](_1: Expr.Of[Rng[A]], _2: B) extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = ReplaceExpr(_1, { (e: A) => _2 })
}
