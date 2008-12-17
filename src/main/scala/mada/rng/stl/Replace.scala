

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Replace extends Replace; trait Replace extends Predefs {
    class MadaRngStlReplace[A](_1: Expr.Of[Rng[A]]) {
        def replace(_2: A => A) = ReplaceExpr(_1, _2).expr
    }
    implicit def toMadaRngStlReplace[A](_1: Expr.Of[Rng[A]]): MadaRngStlReplace[A] = new MadaRngStlReplace[A](_1)
}


case class ReplaceExpr[A](_1: Expr.Of[Rng[A]], _2: A => A) extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = ForeachExpr(OutdirectExpr(_1), { (p: Pointer[A]) => *(p) = _2(*(p)) })
}
