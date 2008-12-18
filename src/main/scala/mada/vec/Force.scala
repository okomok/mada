

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Force extends Force; trait Force extends Predefs {
    class MadaVecForce[A](_1: Expr.Of[Vector[A]]) {
        def force = ForceExpr(_1).expr
    }
    implicit def toMadaVecForce[A](_1: Expr.Of[Vector[A]]): MadaVecForce[A] = new MadaVecForce[A](_1)
}


case class ForceExpr[A](_1: Expr.Of[Vector[A]]) extends Expr.Alias[Vector[A], Vector[A]] {
    override protected def _alias = jcl.FromArrayListExpr(Expr.Cut(jcl.ToArrayListExpr(_1)))
}
