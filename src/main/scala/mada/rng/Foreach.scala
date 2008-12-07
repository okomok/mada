
package mada.rng


object Foreach extends Foreach; trait Foreach extends Predefs {
    class MadaRngForeach[A](_1: Expr.Of[Rng[A]]) {
        def foreach(_2: A => Any) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaRngForeach[A](_1: Expr.Of[Rng[A]]): MadaRngForeach[A] = new MadaRngForeach[A](_1)
}


case class ForeachExpr[A](_1: Expr.Of[Rng[A]], _2: A => Any) extends Expr.Alias[Rng[A], Unit] {
    override protected def _alias = LoopExpr(_1, { (e: A) => _2(e); true })
}
