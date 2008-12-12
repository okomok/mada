

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Clear extends Clear; trait Clear extends Predefs {
    class MadaRngClear[A](_1: Expr.Of[Rng[A]]) {
        def clear = ClearExpr(_1).expr
    }
    implicit def toMadaRngClear[A](_1: Expr.Of[Rng[A]]): MadaRngClear[A] = new MadaRngClear[A](_1)
}


case class ClearExpr[A](override val _1: Expr.Of[Rng[A]]) extends Expr.Transform[Rng[A]] {
    override protected def _default = _1 match {
        case y @ ClearExpr(x1) => y.eval // clear-clear fusion
        case _ => ClearImpl(_1.eval)
    }
}


object ClearImpl {
    def apply[A](r: Rng[A]): Rng[A] = { val q = r.end; q <=< q }
}
