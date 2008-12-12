

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Pointer._


// copyTo

object CopyTo extends CopyTo; trait CopyTo extends Predefs {
    class MadaRngCopyTo[From](_1: Expr.Of[Rng[From]]) {
        def copyTo[To >: From](_2: Expr.Of[Pointer[To]]) = CopyToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyTo[From](_1: Expr.Of[Rng[From]]): MadaRngCopyTo[From] = new MadaRngCopyTo[From](_1)
}

case class CopyToExpr[From, To >: From](override val _1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]])
        extends Expr.Method[Rng[From], Pointer[To]] {
    override protected def _default = CopyToImpl(_1.eval, _2.eval)
}

object CopyToImpl {
    def apply[From, To >: From](r1: Rng[From], _p2: Pointer[To]): Pointer[To] = {
        val (p1, q1) = r1.toPair
        val p2 = _p2.copyIn(Traversal.Forward)

        while (p1 != q1) {
            *(p2) = *(p1)
            ++(p2); ++(p1)
        }
        p2
    }
}


// copyBackwardTo

object CopyBackwardTo extends CopyBackwardTo; trait CopyBackwardTo extends Predefs {
    class MadaRngCopyBackwardTo[From](_1: Expr.Of[Rng[From]]) {
        def copyBackwardTo[To >: From](_2: Expr.Of[Pointer[To]]) = CopyBackwardToExpr(_1, _2).expr
    }
    implicit def toMadaRngCopyBackwardTo[From](_1: Expr.Of[Rng[From]]): MadaRngCopyBackwardTo[From] = new MadaRngCopyBackwardTo[From](_1)
}

case class CopyBackwardToExpr[From, To >: From](override val _1: Expr.Of[Rng[From]], _2: Expr.Of[Pointer[To]])
        extends Expr.Method[Rng[From], Pointer[To]] {
    override protected def _default = CopyBackwardToImpl(_1.eval, _2.eval)
}

object CopyBackwardToImpl {
    def apply[From, To >: From](r1: Rng[From], _q2: Pointer[To]): Pointer[To] = {
        AssertModels(r1, Traversal.Bidirectional)
        AssertModels(_q2, Traversal.Bidirectional)
        val (p1, q1) = r1.toPair
        val q2 = _q2.copy

        while (p1 != q1) {
            *(--(q2)) = *(--(q1))
        }
        q2
    }
}
