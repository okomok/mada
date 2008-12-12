

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import FoldLeft._
import Outdirect._
import Size._


object Distance extends Distance; trait Distance extends Predefs {
    class MadaRngDistance[A](_1: Expr.Of[Rng[A]]) {
        def distance = DistanceExpr(_1).expr
    }
    implicit def toMadaRngDistance[A](_1: Expr.Of[Rng[A]]): MadaRngDistance[A] = new MadaRngDistance[A](_1)
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
