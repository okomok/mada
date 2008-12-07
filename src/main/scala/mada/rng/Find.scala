
package mada.rng


import Loop._


object Find extends Find; trait Find extends Predefs {
    class MadaRngFind[A](_1: Expr.Of[Rng[A]]) {
        def find(_2: A => Boolean) = FindExpr(_1, _2).expr
    }
    implicit def toMadaRngFind[A](_1: Expr.Of[Rng[A]]): MadaRngFind[A] = new MadaRngFind[A](_1)
}


case class FindExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Option[A]] {
    override def _default = {
        val acc = new Ref[Option[A]](None)
        // Prefer Loop to FindPointerOf so a fusion is enabled.
        _1.loop({ (e: A) => if (_2(e)) { acc := Some(e); false } else true }).eval
        acc.deref
    }
}
