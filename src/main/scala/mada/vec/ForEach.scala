

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Foreach extends Foreach; trait Foreach extends Predefs {
    class MadaVecForeach[A](_1: Expr.Of[Vector[A]]) {
        def foreach(_2: A => Any) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaVecForeach[A](_1: Expr.Of[Vector[A]]): MadaVecForeach[A] = new MadaVecForeach[A](_1)
}


case class ForeachExpr[A](_1: Expr.Of[Vector[A]], _2: A => Any) extends Expr.Alias[Vector[A], Unit] {
    override protected def _alias = stl.ForEachExpr(_1, _2)
}
