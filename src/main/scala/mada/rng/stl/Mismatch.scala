

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


import Pointer._


object Mismatch extends Mismatch; trait Mismatch extends Predefs {
    class MadaRngStlMismatch[A1](_1: Expr.Of[Rng[A1]]) {
        def stl_mismatch[A2](_2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean) = MismatchExpr(_1, _2, _3).expr
        def stl_mismatch(_2: Expr.Of[Pointer[A1]]) = MismatchExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngStlMismatch[A1](_1: Expr.Of[Rng[A1]]): MadaRngStlMismatch[A1] = new MadaRngStlMismatch[A1](_1)
}


case class MismatchExpr[A1, A2](override val _1: Expr.Of[Rng[A1]], _2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean)
        extends Expr.Method[Rng[A1], (Pointer[A1], Pointer[A2])] {
    override protected def _default = MismatchImpl(_1.eval, _2.eval, _3)
}


object MismatchImpl {
    def apply[A1, A2](r1: Rng[A1], _p2: Pointer[A2], f: (A1, A2) => Boolean): (Pointer[A1], Pointer[A2]) = {
        val (p1, q1) = r1.toPair
        val p2 = _p2.copyIn(Traversal.Forward)

        while (p1 != q1 && f(*(p1), *(p2))) {
            ++(p1); ++(p2)
        }
        (p1, p2)
    }
}
