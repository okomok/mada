

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Window extends Window; trait Window extends Predefs {
    class MadaVecWindow[A](_1: Expr.Of[Vector[A]]) {
        def window(_2: Long, _3: Long) = WindowExpr(_1, _2, _3).expr
    }
    implicit def toMadaVecWindow[A](_1: Expr.Of[Vector[A]]): MadaVecWindow[A] = new MadaVecWindow[A](_1)
}


case class WindowExpr[A](override val _1: Expr.Of[Vector[A]], _2: Long, _3: Long) extends Expr.Transform[Vector[A]] {
    override protected def _default = _1 match {
        case WindowExpr(x1, x2, x3) => WindowExpr(x1, x2 + _2, x2 + _3).eval // window-window fusion
        case _ => new WindowVector(_1.eval, _2, _3)
    }
}


class WindowVector[A](override val * : Vector[A], n: Long, m: Long) extends Vector.Adapter[A, A] {
    override def size = m - n
    override def apply(i: Long) = *(n + i)
    override def update(i: Long, e: A) = *(n + i) = e
}
