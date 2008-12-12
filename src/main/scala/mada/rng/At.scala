
package mada.rng


import Distance._
import Pointer._


object At extends At; trait At extends Predefs {
    class MadaRngAt[A](_1: Expr.Of[Rng[A]]) {
        def at(_2: Long) = AtExpr(_1, _2).expr
    }
    implicit def toMadaRngAt[A](_1: Expr.Of[Rng[A]]): MadaRngAt[A] = new MadaRngAt[A](_1)
}


case class AtExpr[A](override val _1: Expr.Of[Rng[A]], _2: Long) extends Expr.Method[Rng[A], A] {
    override protected def _default = AtImpl(_1.eval, _2)
}


object AtImpl {
    def apply[A](r: Rng[A], i: Long): A = {
        AssertModels(r, Traversal.RandomAccess)
        Assert("out of Rng", 0 <= i)
        Assert("out of Rng", i < r./.distance./)
        *(r.begin + i)
    }
}
