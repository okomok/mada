

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object Find extends Find; trait Find extends Predefs {
    class MadaVecStlFind[A](_1: Expr.Of[Vector[A]]) {
        def stl_find(_2: A) = FindExpr(_1, _2).expr
        def stl_findIf(_2: A => Boolean) = FindIfExpr(_1, _2).expr
    }
    implicit def toMadaVecStlFind[From](_1: Expr.Of[Vector[From]]): MadaVecStlFind[From] = new MadaVecStlFind[From](_1)
}


case class FindExpr[A](_1: Expr.Of[Vector[A]], _2: A) extends Expr.Alias[Vector[A], Long] {
    override protected def _alias = FindIfExpr(_1, (_: A) == _2)
}

case class FindIfExpr[A](override val _1: Expr.Of[Vector[A]], _2: A => Boolean) extends Expr.Method[Vector[A], Long] {
    override protected def _default = {
        var i = 0
        LoopExpr(_1, {(e: A) => if (_2(e)) { false } else { i += 1; true } })
        i
    }
}
