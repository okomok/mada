
package mada.rng


object Find extends Find

trait Find extends Predefs {
    class MadaRngFind[A](_1: Expr[Rng[A]]) {
        def rng_find(_2: A => Boolean) = FindExpr(_1, _2).expr
    }
    implicit def toMadaRngFind[A](_1: Expr[Rng[A]]): MadaRngFind[A] = new MadaRngFind[A](_1)
}


case class FindExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Option[A]] {
    override def _eval = {
        val acc = new Ref[Option[A]](None)
        // Prefer Loop to FindPointerOf so a fusion is enabled.
        LoopExpr(_1, { (e: A) => if (_2(e)) { acc := Some(e); false } else true }).eval
        acc.deref
    }
}
