

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object ForEach extends ForEach; trait ForEach extends Predefs {
    class MadaVecStlForEach[A](_1: Expr.Of[Vector[A]]) {
        def stl_forEach[F <: (A => Any)](_2: F) = ForEachExpr(_1, _2).expr
    }
    implicit def toMadaVecStlForEach[A](_1: Expr.Of[Vector[A]]): MadaVecStlForEach[A] = new MadaVecStlForEach[A](_1)
}


case class ForEachExpr[A, F <: (A => Any)](override val _1: Expr.Of[Vector[A]], _2: F) extends Expr.Method[Vector[A], F] {
    override protected def _default = {
        LoopExpr(_1, { (e: A) => _2(e); true }).eval
        _2
    }
}
