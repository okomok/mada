

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Drop extends Drop; trait Drop extends Predefs {
    class MadaVecDrop[A](_1: Expr.Of[Vector[A]]) {
        def drop(_2: Long) = DropExpr(_1, _2).expr
    }
    implicit def toMadaVecDrop[A](_1: Expr.Of[Vector[A]]): MadaVecDrop[A] = new MadaVecDrop[A](_1)
}


case class DropExpr[A](override val _1: Expr.Of[Vector[A]], _2: Long) extends Expr.Transform[Vector[A]] {
    override protected def _default = _1 match {
        case DropExpr(x1, x2) => DropExpr(x1, x2 + _2).eval // drop-drop fusion
        case _ => {
            val v = _1.eval
            new WindowVector(v, Math.min(_2, v.size), v.size)
        }
    }
}
