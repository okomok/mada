

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Reverse extends Reverse; trait Reverse extends Predefs {
    class MadaVecReverse[A](_1: Expr.Of[Vector[A]]) {
        def reverse = ReverseExpr(_1).expr
    }
    implicit def toMadaVecReverse[A](_1: Expr.Of[Vector[A]]): MadaVecReverse[A] = new MadaVecReverse[A](_1)
}


case class ReverseExpr[A](_1: Expr.Of[Vector[A]]) extends Expr[Vector[A], Vector[A]] {
    override protected def _eval[U](x: Expr[Vector[A], U]): U = x match {
        case Self => _1?this
        case Unknown => _1 match {
            case ReverseExpr(x1) => x1.eval // reverse-reverse fusion
            case _ => new ReverseVector(_1.eval)
        }
        case _ => dontKnow(x)
    }
}


class ReverseVector[A](override val * : Vector[A]) extends Vector.Adapter[A, A] {
    override def apply(i: Long) = *(size - i - 1)
    override def update(i: Long, e: A) = *(size - i - 1) = e
}
