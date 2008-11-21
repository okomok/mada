
package mada.rng


object Foreach extends Foreach

trait Foreach extends Predefs {
    class MadaRngForeach[A](_1: Expr[Rng[A]]) {
        def rng_foreach[X](_2: A => X) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaRngForeach[A](_1: Expr[Rng[A]]): MadaRngForeach[A] = new MadaRngForeach[A](_1)
}


case class ForeachExpr[A, X](_1: Expr[Rng[A]], _2: A => X) extends Expr[Unit] {
    override def _eval[U](c: Context[Unit, U]) = LoopExpr(_1, { (e: A) => _2(e); true }).eval(c)
}
