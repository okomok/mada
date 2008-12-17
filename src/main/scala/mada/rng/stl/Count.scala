

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng.stl


object Count extends Count; trait Count extends Predefs {
    class MadaRngStlCount[A](_1: Expr.Of[Rng[A]]) {
        def count(_2: A) = CountExpr(_1, _2).expr
        def countIf(_2: A => Boolean) = CountIfExpr(_1, _2).expr
    }
    implicit def toMadaRngStlCount[A](_1: Expr.Of[Rng[A]]): MadaRngStlCount[A] = new MadaRngStlCount[A](_1)
}


case class CountExpr[A](_1: Expr.Of[Rng[A]], _2: A) extends Expr.Alias[Rng[A], Long] {
    override protected def _alias = CountIfExpr[A](_1, _ == _2)
}

case class CountIfExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Long] {
    override protected def _default = CountIfImpl(_1.eval, _2)
}


object CountIfImpl {
    import Foreach._

    def apply[A](r: Rng[A], f: A => Boolean): Long = {
        var c = 0L
        r./.foreach({ (e: A) => if (f(e)) c += 1L })./
        c
    }
}
