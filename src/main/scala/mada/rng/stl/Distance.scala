

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


import FoldLeft._
import Outdirect._
import Size._


object Distance extends Distance; trait Distance extends Predefs {
    class MadaRngStlDistance[A](_1: Expr.Of[Rng[A]]) {
        def stl_distance = DistanceExpr(_1).expr
    }
    implicit def toMadaRngStlDistance[A](_1: Expr.Of[Rng[A]]): MadaRngStlDistance[A] = new MadaRngStlDistance[A](_1)
}


case class DistanceExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Method[Rng[A], Long] {
    override protected def _default = DistanceImpl(_1.eval)
}


object DistanceImpl {
    def apply[A](r: Rng[A]): Long = {
        r.traversal match {
            case _: Traversal.RandomAccess => r./.size./
            case _: Traversal.SinglePass => r./.outdirect.foldLeft(0L, { (b: Long, a: Pointer[A]) => b + 1 })./
        }
    }
}
