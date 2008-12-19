

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Reverse extends Reverse; trait Reverse extends Predefs {
    class MadaVecReverse[A](_1: Expr.Of[Vector[A]]) {
        def reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaVecReverse[A](_1: Expr.Of[Vector[A]]): MadaVecReverse[A] = new MadaVecReverse[A](_1)
}


case class ReverseExpr[A](override val _1: Expr.Of[Vector[A]]) extends Expr.Transform[Vector[A]] {
    override protected def _default = _1 match {
        case ReverseExpr(x1) => x1.eval // reverse-reverse fusion
        case _ => new ReverseVector(_1.eval)
    }
}


class ReverseVector[A](override val * : Vector[A]) extends Vector.Adapter[A, A] {
    override def apply(i: Long) = *(size - i - 1)
    override def update(i: Long, e: A) = *(size - i - 1) = e
}
