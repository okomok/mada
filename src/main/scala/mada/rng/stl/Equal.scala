

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Equal extends Equal; trait Equal extends Predefs {
    class MadaRngStlEqual[A1](_1: Expr.Of[Rng[A1]]) {
        def stl_equal(_2: Expr.Of[Pointer[A1]]) = EqualExpr(_1, _2).expr
        def stl_equalIf[A2](_2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean) = EqualIfExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngStlEqual[A1](_1: Expr.Of[Rng[A1]]): MadaRngStlEqual[A1] = new MadaRngStlEqual[A1](_1)
}


case class EqualExpr[A](_1: Expr.Of[Rng[A]], _2: Expr.Of[Pointer[A]]) extends Expr.Alias[Rng[A], Boolean] {
    override protected def _alias = EqualIfExpr[A, A](_1, _2, _ == _)
}

case class EqualIfExpr[A1, A2](override val _1: Expr.Of[Rng[A1]], _2: Expr.Of[Pointer[A2]], _3: (A1, A2) => Boolean)
        extends Expr.Method[Rng[A1], Boolean] {
    override protected def _default = EqualIfImpl(_1.eval, _2.eval, _3)
}


object EqualIfImpl {
    import Mismatch._

    def apply[A1, A2](r1: Rng[A1], p2: Pointer[A2], f: (A1, A2) => Boolean): Boolean = {
        r1./.stl_mismatchIf(p2, f)./._1 == r1.end
    }
}
