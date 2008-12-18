

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Copy extends Copy; trait Copy extends Predefs {
    class MadaVecStlCopy[A](_1: Expr.Of[Vector[A]]) {
        def stl_copy[F <: (A => Any)](_2: F) = CopyExpr(_1, _2).expr
        def stl_copyIf[F <: (A => Any)](_2: F, _3: A => Boolean) = CopyIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecStlCopy[A](_1: Expr.Of[Vector[A]]): MadaVecStlCopy[A] = new MadaVecStlCopy[A](_1)
}


case class CopyExpr[A, F <: (A => Any)](_1: Expr.Of[Vector[A]], _2: F) extends Expr.Alias[Vector[A], F] {
    override protected def _alias = ForEachExpr(_1, _2)
}

case class CopyIfExpr[A, F <: (A => Any)](override val _1: Expr.Of[Vector[A]], _2: F, _3: A => Boolean) extends Expr.Method[Vector[A], F] {
    override protected def _default = {
        ForEachExpr(_1, { (e: A) => if (_3(e)) _2(e) }).eval
        _2
    }
}
