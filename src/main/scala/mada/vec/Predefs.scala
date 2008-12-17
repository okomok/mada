

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Predefs extends Predefs; trait Predefs extends Namespace
        with Compatibles {
    implicit def madaVecVector2ExprOfVector[A](v: Vector[A]): Expr.Of[Vector[A]] = Expr(v)
}
