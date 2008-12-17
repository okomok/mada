

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object ForEach extends ForEach; trait ForEach extends Predefs {
    class MadaVecStlForEach[A](_1: Expr.Of[Vector[A]]) {
        def stl_forEach(_2: A => Any) = ForEachExpr(_1, _2).expr
    }
    implicit def toMadaVecStlForEach[A](_1: Expr.Of[Vector[A]]): MadaVecStlForEach[A] = new MadaVecStlForEach[A](_1)
}


case class ForEachExpr[A](_1: Expr.Of[Vector[A]], _2: A => Any) extends Expr.Alias[Vector[A], Unit] {
    override protected def _alias = LoopExpr(_1, { (e: A) => _2(e); true })
}
