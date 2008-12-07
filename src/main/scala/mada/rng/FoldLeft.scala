
package mada.rng


import Foreach._


object FoldLeft extends FoldLeft; trait FoldLeft extends Predefs {
    class MadaRngFoldLeft[A](_1: Expr.Of[Rng[A]]) {
        def foldLeft[B](_2: B, _3: (B, A) => B) = FoldLeftExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngFoldLeft[A](_1: Expr.Of[Rng[A]]): MadaRngFoldLeft[A] = new MadaRngFoldLeft[A](_1)
}


case class FoldLeftExpr[A, B](override val _1: Expr.Of[Rng[A]], _2: B, _3: (B, A) => B) extends Expr.Method[Rng[A], B] {
    override def _default = {
        val acc = new Ref(_2)
        _1.foreach(acc := _3(acc.deref, _)).eval
        acc.deref
    }
}
