
package mada.rng


object Foreach extends Foreach; trait Foreach extends Predefs {
    class MadaRngForeach[A](_1: Expr[Rng[A]]) {
        def foreach(_2: A => Any) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaRngForeach[A](_1: Expr[Rng[A]]): MadaRngForeach[A] = new MadaRngForeach[A](_1)
}


case class ForeachExpr[A](_1: Expr[Rng[A]], _2: A => Any) extends ExprAdapter[Unit] {
    override protected def _base = LoopExpr(_1, { (e: A) => _2(e); true })
}
