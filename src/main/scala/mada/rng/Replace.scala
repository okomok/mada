

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Replace extends Replace; trait Replace extends Predefs {
    class MadaRngReplace[A](_1: Expr.Of[Rng[A]]) {
        def replace(_2: A => A) = ReplaceExpr(_1, _2).expr
    }
    implicit def toMadaRngReplace[A](_1: Expr.Of[Rng[A]]): MadaRngReplace[A] = new MadaRngReplace[A](_1)
}


case class ReplaceExpr[A](_1: Expr.Of[Rng[A]], _2: A => A) extends Expr.Alias[Rng[A], Unit] {
    import Pointer._
    override protected def _alias = ForeachExpr(OutdirectExpr(_1), { (p: Pointer[A]) => *(p) = _2(*(p)) })
}
