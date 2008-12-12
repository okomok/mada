

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Implies._


object Window extends Window; trait Window extends Predefs {
    class MadaRngWindow[A](_1: Expr.Of[Rng[A]]) {
        def window(_2: Long, _3: Long) = WindowExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngWindow[A](_1: Expr.Of[Rng[A]]): MadaRngWindow[A] = new MadaRngWindow[A](_1)
}


case class WindowExpr[A](override val _1: Expr.Of[Rng[A]], _2: Long, _3: Long) extends Expr.Transform[Rng[A]] {
    override protected def _default = _1 match {
        case WindowExpr(x1, x2, x3) => WindowExpr(x1, x2 + _2, x2 + _3).eval // window-window fusion
        case _ => WindowImpl(_1.eval, _2, _3)
    }
}


object WindowImpl {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = {
        AssertModels(r, Traversal.Forward)
        Assert("requires n <= m", n <= m)
        Assert("requires BidirectionalRng", (n < 0) implies (r models Traversal.Bidirectional))

        val p = r.begin.advance(n)
        p <=< p.copy.advance(m - n)
    }
}
