

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Slice extends Slice; trait Slice extends Predefs {
    class MadaVecSlice[A](_1: Expr.Of[Vector[A]]) {
        def slice(_2: Long, _3: Long) = SliceExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecSlice[A](_1: Expr.Of[Vector[A]]): MadaVecSlice[A] = new MadaVecSlice[A](_1)
}


case class SliceExpr[A](_1: Expr.Of[Vector[A]], _2: Long, _3: Long) extends Expr.Alias[Vector[A], Vector[A]] {
    override protected def _alias = TakeExpr(DropExpr(_1, _2), _3 - _2)
}
