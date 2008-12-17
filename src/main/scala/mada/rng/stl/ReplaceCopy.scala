

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object ReplaceCopy extends ReplaceCopy; trait ReplaceCopy extends Predefs {
    class MadaRngStlReplaceCopy[From](_1: Expr.Of[Rng[From]]) {
        def replaceCopy[To >: From](_2: Expr.Of[Pointer[To]], _3: From, _4: To) = ReplaceCopyExpr(_1, _2, _3, _4).expr
        def replaceCopyIf[To >: From](_2: Expr.Of[Pointer[To]], _3: From => Boolean, _4: To) = ReplaceCopyIfExpr(_1, _2, _3, _4).expr
    }
    implicit def toMadaRngStlReplaceCopy[From](_1: Expr.Of[Rng[From]]): MadaRngStlReplaceCopy[From] = new MadaRngStlReplaceCopy[From](_1)
}


case class ReplaceCopyExpr[From, To >: From](_1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]], _3: From, _4: To) extends Expr.Alias[Rng[From], Pointer[To]] {
    override protected def _alias = ReplaceCopyIfExpr(_1, _2, (_: From) == _3, _4)
}

case class ReplaceCopyIfExpr[From, To >: From](_1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]], _3: From => Boolean, _4: To) extends Expr.Alias[Rng[From], Pointer[To]] {
    override protected def _alias = CopyExpr(MapExpr(_1, { (e: From) => if (_3(e)) _4 else e }), _2)
}
