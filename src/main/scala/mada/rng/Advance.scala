

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


object Advance extends Advance; trait Advance extends Predefs {
    class MadaRngAdvance[A](_1: Expr.Of[Pointer[A]]) {
        def advance(_2: Long) = AdvanceExpr(_1, _2).expr
    }
    implicit def toMadaRngAdvance[A](_1: Expr.Of[Pointer[A]]): MadaRngAdvance[A] = new MadaRngAdvance[A](_1)
}


case class AdvanceExpr[A](override val _1: Expr.Of[Pointer[A]], _2: Long)
        extends Expr.Method[Pointer[A], Pointer[A]] {
    override protected def _default = AdvanceImpl(_1.eval, _2)
}


object AdvanceImpl {
    def apply[A](p: Pointer[A], dd : Long): Pointer[A] = {
        var d = dd
        p.traversal match {
            case _: Traversal.RandomAccess => p += d
            case _: Traversal.Bidirectional => {
                if (d >= 0)
                    while (d != 0) { ++(p); d -= 1 }
                else
                    while (d != 0) { --(p); d += 1 }
            }
            case _: Traversal.SinglePass => while (d != 0) { ++(p); d -= 1 }
        }
        p
    }
}
