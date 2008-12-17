

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.rng


object Find extends Find; trait Find extends Predefs {
    class MadaRngFind[A](_1: Expr.Of[Rng[A]]) {
        def find(_2: A => Boolean) = FindExpr(_1, _2).expr
    }
    implicit def toMadaRngFind[A](_1: Expr.Of[Rng[A]]): MadaRngFind[A] = new MadaRngFind[A](_1)
}


case class FindExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Option[A]] {
    override protected def _default = FindImpl(_1.eval, _2)
}


object FindImpl {
    import Loop._

    def apply[A](r: Rng[A], f: A => Boolean): Option[A] = {
        var acc: Option[A] = None
        // Prefer loop to stl_findIf so a fusion is enabled.
        r./.loop({ (e: A) => if (f(e)) { acc = Some(e); false } else true })./
        acc
    }
}
