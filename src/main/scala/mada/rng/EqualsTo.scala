

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Mismatch._


object EqualsTo extends EqualsTo; trait EqualsTo extends Predefs {
    class MadaRngEqualsTo[A1](_1: Expr.Of[Rng[A1]]) {
        def equalsTo[A2](_2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean) = EqualsToExpr(_1, _2, _3).expr
        def equalsTo(_2: Expr.Of[Pointer[A1]]) = EqualsToExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngEqualsTo[A1](_1: Expr.Of[Rng[A1]]): MadaRngEqualsTo[A1] = new MadaRngEqualsTo[A1](_1)
}


case class EqualsToExpr[A1, A2](override val _1: Expr.Of[Rng[A1]], _2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean)
        extends Expr.Method[Rng[A1], Boolean] {
    override protected def _default = EqualsToImpl(_1.eval, _2.eval, _3)
}


object EqualsToImpl {
    def apply[A1, A2](r1: Rng[A1], p2: Pointer[A2], f: (A1, A2) => Boolean): Boolean = {
        r1./.mismatch(p2, f)./._1 == r1.end
    }
}
