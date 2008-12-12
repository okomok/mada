

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


import Foreach._


object Count extends Count; trait Count extends Predefs {
    class MadaRngCount[A](_1: Expr.Of[Rng[A]]) {
        def count(_2: A => Boolean) = CountExpr(_1, _2).expr
    }
    implicit def toMadaRngCount[A](_1: Expr.Of[Rng[A]]): MadaRngCount[A] = new MadaRngCount[A](_1)
}


case class CountExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Long] {
    override protected def _default = CountImpl(_1.eval, _2)
}


object CountImpl {
    def apply[A](r: Rng[A], f: A => Boolean): Long = {
        var c = 0L
        r./.foreach({ (e: A) => if (f(e)) c += 1L })./
        c
    }
}
