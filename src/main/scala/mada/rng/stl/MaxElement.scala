

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object MaxElement extends MaxElement; trait MaxElement extends Predefs {
    class MadaRngStlMaxElement[A](_1: Expr.Of[Rng[A]]) {
        def stl_maxElement(implicit _2: A => Ordered[A]) = MaxElementExpr(_1, _2).expr
        def stl_maxElementWith(_2: (A, A) => Boolean) = MaxElementWithExpr(_1, _2).expr
    }
    implicit def toMadaRngStlMaxElement[A](_1: Expr.Of[Rng[A]]): MadaRngStlMaxElement[A] = new MadaRngStlMaxElement[A](_1)
}


case class MaxElementExpr[A](_1: Expr.Of[Rng[A]], _2: A => Ordered[A]) extends Expr.Alias[Rng[A], Pointer[A]] {
    override protected def _alias = MaxElementWithExpr[A](_1.eval, _2(_) < _)
}

case class MaxElementWithExpr[A](_1: Expr.Of[Rng[A]], _2: (A, A) => Boolean) extends Expr.Alias[Rng[A], Pointer[A]] {
    override protected def _alias = MinElementWithExpr(_1, { (x: A, y: A) => _2(y, x) })
}
