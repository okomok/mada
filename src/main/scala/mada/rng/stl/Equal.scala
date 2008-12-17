

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


import Mismatch._


object Equal extends Equal; trait Equal extends Predefs {
    class MadaRngStlEqual[A1](_1: Expr.Of[Rng[A1]]) {
        def stl_equal[A2](_2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean) = EqualExpr(_1, _2, _3).expr
        def stl_equal(_2: Expr.Of[Pointer[A1]]) = EqualExpr[A1, A1](_1, _2, _ == _).expr
    }
    implicit def toMadaRngStlEqual[A1](_1: Expr.Of[Rng[A1]]): MadaRngStlEqual[A1] = new MadaRngStlEqual[A1](_1)
}


case class EqualExpr[A1, A2](override val _1: Expr.Of[Rng[A1]], _2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean)
        extends Expr.Method[Rng[A1], Boolean] {
    override protected def _default = EqualImpl(_1.eval, _2.eval, _3)
}


object EqualImpl {
    def apply[A1, A2](r1: Rng[A1], p2: Pointer[A2], f: (A1, A2) => Boolean): Boolean = {
        r1./.stl_mismatch(p2, f)./._1 == r1.end
    }
}
