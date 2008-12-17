

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Transform extends Transform; trait Transform extends Predefs {
    class MadaRngStlTransform[From](_1: Expr.Of[Rng[From]]) {
        def transform[To >: From](_2: Expr.Of[Pointer[To]], _3: From => To) = TransformExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlTransform[From](_1: Expr.Of[Rng[From]]): MadaRngStlTransform[From] = new MadaRngStlTransform[From](_1)
}


case class TransformExpr[From, To >: From](_1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]], _3: From => To) extends Expr.Alias[Rng[From], Pointer[To]] {
    override protected def _alias = CopyExpr(MapExpr(_1, _3), _2)
}
