
package mada.rng


import Loop._


object Foreach extends Foreach; trait Foreach extends Predefs {
    class MadaRngForeach[A](_1: Expr[Rng[A]]) {
        def foreach(_2: A => Any) = ForeachExpr(_1, _2).expr
    }
    implicit def toMadaRngForeach[A](_1: Expr[Rng[A]]): MadaRngForeach[A] = new MadaRngForeach[A](_1)
}


case class ForeachExpr[A](_1: Expr[Rng[A]], _2: A => Any) extends Expr[Unit] {
    override def _eval[U](c: Context[Unit, U]) = _1.loop({ (e: A) => _2(e); true }).eval(c)
}
