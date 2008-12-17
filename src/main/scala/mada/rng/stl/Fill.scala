

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Fill extends Fill; trait Fill extends Predefs {
    class MadaRngStlFill[A](_1: Expr.Of[Rng[A]]) {
        def stl_fill(_2: A) = FillExpr(_1, _2).expr
    }
    implicit def toMadaRngStlFill[A](_1: Expr.Of[Rng[A]]): MadaRngStlFill[A] = new MadaRngStlFill[A](_1)
}


case class FillExpr[A](_1: Expr.Of[Rng[A]], _2: A) extends Expr.Alias[Rng[A], Unit] {
    import Pointer._
    override protected def _alias = ForeachExpr(OutdirectExpr(_1), *(_: Pointer[A]) = _2)
}
