

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Take extends Take; trait Take extends Predefs {
    class MadaVecTake[A](_1: Expr.Of[Vector[A]]) {
        def take(_2: Long) = TakeExpr(_1, _2).expr
    }
    implicit def toMadaVecTake[A](_1: Expr.Of[Vector[A]]): MadaVecTake[A] = new MadaVecTake[A](_1)
}


case class TakeExpr[A](override val _1: Expr.Of[Vector[A]], _2: Long) extends Expr.Transform[Vector[A]] {
    override protected def _default = _1 match {
        case TakeExpr(x1, x2) => TakeExpr(x1, Math.min(x2, _2)).eval // take-take fusion
        case _ => {
            val v = _1.eval
            new WindowVector(v, 0, Math.min(_2, v.size))
        }
    }
}
