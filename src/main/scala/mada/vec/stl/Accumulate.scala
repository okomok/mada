

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Accumulate extends Accumulate; trait Accumulate extends Predefs {
    class MadaVecAccumulate[A](_1: Expr.Of[Vector[A]]) {
        def stl_accumulate[B](_2: B, _3: (B, A) => B) = AccumulateExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecAccumulate[A](_1: Expr.Of[Vector[A]]): MadaVecAccumulate[A] = new MadaVecAccumulate[A](_1)
}


case class AccumulateExpr[A, B](override val _1: Expr.Of[Vector[A]], _2: B, _3: (B, A) => B) extends Expr.Method[Vector[A], B] {
    override protected def _default = {
        var init = _2
        ForeachExpr(_1, { (e: A) => init = _3(init, e) }).eval // enables fusion.
        init
    }
}
