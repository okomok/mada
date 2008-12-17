

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Replace extends Replace; trait Replace extends Predefs {
    class MadaRngStlReplace[A](_1: Expr.Of[Rng[A]]) {
        def replace(_2: A, _3: A) = ReplaceExpr(_1, _2, _3).expr
        def replaceIf(_2: A => Boolean, _3: A) = ReplaceIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlReplace[A](_1: Expr.Of[Rng[A]]): MadaRngStlReplace[A] = new MadaRngStlReplace[A](_1)
}


case class ReplaceExpr[A](_1: Expr.Of[Rng[A]], _2: A, _3: A) extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = ReplaceIfExpr(_1, (_: A) == _2, _3)
}

case class ReplaceIfExpr[A](_1: Expr.Of[Rng[A]], _2: A => Boolean, _3: A) extends Expr.Alias[Rng[A], Unit] {
    import Pointer._
    override protected def _alias = ForeachExpr(OutdirectExpr(_1), { (p: Pointer[A]) => if (_2(*(p))) { *(p) = _3 } })
}
